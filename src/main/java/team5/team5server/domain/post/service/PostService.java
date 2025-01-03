package team5.team5server.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.team5server.domain.image.domain.Image;
import team5.team5server.domain.image.domain.repository.ImageRepository;
import team5.team5server.domain.image.service.FileStore;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.domain.post.domain.PostCategory;
import team5.team5server.domain.post.domain.PostType;
import team5.team5server.domain.post.domain.repository.PostRepository;
import team5.team5server.domain.post.dto.request.PostEditRequest;
import team5.team5server.domain.post.dto.request.PostSaveRequest;
import team5.team5server.domain.post.dto.response.*;
import team5.team5server.domain.user.domain.User;
import team5.team5server.domain.user.domain.repository.UserRepository;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    public PostSaveResponse uploadPost(PostSaveRequest postSaveRequest, List<String> fileKeys) {
        //파일 경로 찾기
        List<Image> images = new ArrayList<>();

        Long userId = postSaveRequest.getUserId();
        User writer = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_USER));

        PostCategory postCategory = PostCategory.from(postSaveRequest.getCategory());
        PostType postType = PostType.from(postSaveRequest.getType());
        log.info("Saving Category: {}", postCategory);

        Post newPost = Post.builder()
                .user(writer)
                .title(postSaveRequest.getTitle())
                .contents(postSaveRequest.getContents())
                .organizer(postSaveRequest.getOrganizer())
                .organizerLink(postSaveRequest.getOrganizer_link())
                .place(postSaveRequest.getPlace())
                .postType(postType)
                .postCategory(postCategory)
                .startTime(postSaveRequest.getStartTime())
                .endTime(postSaveRequest.getEndTime())
                .build();

        if (fileKeys != null && !fileKeys.isEmpty()) {
            for (String key : fileKeys) {
                images.add(Image.builder()
                        .filePath(fileStore.getFullPath(key))
                        .post(newPost)
                        .build());
            }

        }
        newPost.getImages().addAll(images);

        log.info("Saved Category: {}", postCategory);
        Post savePost = postRepository.save(newPost);
        imageRepository.saveAll(images);
        return PostSaveResponse.of(savePost);
    }


    public PostListResponse viewPost(String category, int order) {

        List<PostInfo> posts = getPostInfoList(category, order);

        return PostListResponse.of(posts);
    }

    private List<PostInfo> getPostInfoList(String category, int order) {
        PostCategory postCategory = PostCategory.from(category);
        String sortMethod;
        Sort.Direction sortDirection = Sort.Direction.ASC;

        if (order == 0) {
            sortMethod = "createdAt";
            sortDirection = Sort.Direction.DESC;
        } else if (order == 1) {
            sortMethod = "viewCount";
            sortDirection = Sort.Direction.DESC;
        } else if (order == 2) {
            sortMethod = "startTime";
        } else {
            throw new CustomException(ErrorCode.NO_SUCH_ORDER);
        }

        List<Post> findPosts = postRepository.findByPostCategory(postCategory, Sort.by(sortDirection, sortMethod));
        if (findPosts.isEmpty()) {
            throw new CustomException(ErrorCode.NO_SUCH_POST);
        }
        List<PostInfo> posts = getPostInfo(findPosts);
        return posts;
    }

    private List<PostInfo> getPostInfo(List<Post> findPosts) {
        List<PostInfo> posts = new ArrayList<>();

        for (Post post : findPosts) {

            List<String> imageUrls = new ArrayList<>();
            for (Image image : post.getImages()) {
                log.info("Post id: {}, Post imagesURl: {}", post.getId(), image.getFilePath());
                imageUrls.add(fileStore.getPublicUrl(image.getFilePath()));
            }

            PostInfo postInfo = PostInfo.builder()
                    .postId(post.getId())
                    .userId(post.getUser().getId())
                    .title(post.getTitle())
                    .contents(post.getContents())
                    .type(post.getPostType().getType())
                    .place(post.getPlace())
                    .startTime(post.getStartTime())
                    .endTime(post.getEndTime())
                    .reportCount(post.getReportCount())
                    .createdDate(post.getCreatedAt())
                    .imageUrls(imageUrls)
                    .build();
            posts.add(postInfo);
        }
        return posts;
    }

    public PostListResponse viewPostAll(String category, int order) { //정렬된 post list와 인기있는 post 3개 반환
        List<PostInfo> posts = getPostInfoList(category, order);

        PostCategory postCategory = PostCategory.from(category);

        List<Post> findPosts = postRepository.findTop3ByPostCategoryOrderByViewCountDesc(postCategory);
        List<PostInfo> popularPosts = getPostInfo(findPosts);


        return PostListResponse.of(posts, popularPosts);
    }

    public PostInfoResponse viewPostInfo(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_POST));

        int viewCount = findPost.getViewCount()+1;
        findPost.setViewCount(viewCount);

        postRepository.save(findPost);

        List<String> imageUrls = findPost.getImages().stream()
                .map(image -> fileStore.getPublicUrl(image.getFilePath()))
                .toList();

        return PostInfoResponse.of(findPost, imageUrls);
    }


    public PostEditResponse editPost(Long postId, PostEditRequest postEditRequest) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_POST));

        findPost.setTitle(postEditRequest.getTitle());
        findPost.setContents(postEditRequest.getContents());
        findPost.setOrganizer(postEditRequest.getOrganizer());
        findPost.setPlace(postEditRequest.getPlace());
        findPost.setStartTime(postEditRequest.getStartTime());
        findPost.setEndTime(postEditRequest.getEndTime());

        postRepository.save(findPost);

        return PostEditResponse.of(findPost);
    }
}
