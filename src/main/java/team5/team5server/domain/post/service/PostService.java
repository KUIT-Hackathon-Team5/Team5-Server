package team5.team5server.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.domain.post.domain.PostCategory;
import team5.team5server.domain.post.domain.PostType;
import team5.team5server.domain.post.domain.repository.PostRepository;
import team5.team5server.domain.post.dto.request.PostSaveRequest;
import team5.team5server.domain.post.dto.response.PostInfo;
import team5.team5server.domain.post.dto.response.PostInfoResponse;
import team5.team5server.domain.post.dto.response.PostListResponse;
import team5.team5server.domain.post.dto.response.PostSaveResponse;
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

    public PostSaveResponse uploadPost(PostSaveRequest postSaveRequest) {

        Long userId = postSaveRequest.getUserId();
        User writer = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_USER));

        PostCategory postCategory = PostCategory.from(postSaveRequest.getCategory());
        PostType postType = PostType.from(postSaveRequest.getType());

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

        Post savePost = postRepository.save(newPost);
        return PostSaveResponse.of(savePost);
    }


    public PostListResponse viewPost(String category, int order) {

        PostCategory postCategory = PostCategory.from(category);
        String sortMethod;

        if (order == 0) {
            sortMethod = "createdAt";
        } else if (order == 1) {
            sortMethod = "viewCount";
        } else if (order == 2) {
            sortMethod = "startTime";
        } else {
            throw new CustomException(ErrorCode.NO_SUCH_ORDER);
        }

        List<Post> findPostCategory = postRepository.findByPostCategory(postCategory, Sort.by(Sort.Direction.ASC, sortMethod));
        List<PostInfo> posts = new ArrayList<>();

        for (Post post : findPostCategory) {
            PostInfo postInfo = PostInfo.builder()
                    .postId(post.getId())
                    .userId(post.getUser().getId())
                    .title(post.getTitle())
                    .contents(post.getContents())
                    .type(post.getPostType().getType())
                    .place(post.getPlace())
                    .startTime(post.getStartTime())
                    .endTime(post.getEndTime())
                    .likeCount(post.getLikeCount())
                    .reportCount(post.getReportCount())
                    .createdDate(post.getCreatedAt())
                    .build();
            posts.add(postInfo);
        }

        return PostListResponse.of(posts);
    }

    public PostInfoResponse viewPostInfo(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_POST));

        int viewCount = findPost.getViewCount()+1;
        findPost.setViewCount(viewCount);

        postRepository.save(findPost);

        return PostInfoResponse.of(findPost);
    }
}
