package team5.team5server.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.domain.post.domain.PostCategory;
import team5.team5server.domain.post.domain.PostType;
import team5.team5server.domain.post.domain.repository.PostRepository;
import team5.team5server.domain.post.dto.request.PostSaveRequest;
import team5.team5server.domain.post.dto.response.PostSaveResponse;
import team5.team5server.domain.user.domain.User;
import team5.team5server.domain.user.domain.repository.UserRepository;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

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
                .category(postCategory)
                .startTime(postSaveRequest.getStartTime())
                .endTime(postSaveRequest.getEndTime())
                .build();

        Post savePost = postRepository.save(newPost);
        return PostSaveResponse.of(savePost);
    }
}
