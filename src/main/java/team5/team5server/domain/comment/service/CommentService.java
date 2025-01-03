package team5.team5server.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.team5server.domain.comment.domain.Comment;
import team5.team5server.domain.comment.domain.repository.CommentRepository;
import team5.team5server.domain.comment.dto.request.CommentSaveRequest;
import team5.team5server.domain.comment.dto.response.CommentInfo;
import team5.team5server.domain.comment.dto.response.CommentListResponse;
import team5.team5server.domain.comment.dto.response.CommentSaveResponse;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.domain.post.domain.repository.PostRepository;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentSaveResponse uploadComment(CommentSaveRequest commentSaveRequest) {

        User user = userRepository.findById(commentSaveRequest.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_USER));

        Post post = postRepository.findById(commentSaveRequest.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_POST));

        Comment newComment = Comment.builder()
                .post(post)
                .user(user)
                .contents(commentSaveRequest.getContents())
                .build();

        commentRepository.save(newComment);
        return CommentSaveResponse.of(newComment);
    }

    public CommentListResponse viewComment(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_POST));

        List<Comment> findComments = commentRepository.findByPost(post, Sort.by(Sort.Direction.ASC, "createdAt"));
        if (findComments.isEmpty()) {
            throw new CustomException(ErrorCode.NO_SUCH_COMMENT);
        }

        List<CommentInfo> comments = new ArrayList<>();

        for (Comment comment : findComments) {
            CommentInfo commentInfo = CommentInfo.builder()
                    .commentId(comment.getId())
                    .postId(comment.getPost().getId())
                    .userId(comment.getUser().getId())
                    .userName(comment.getUser().getName())
                    .contents(comment.getContents())
                    .createdDate(comment.getCreatedAt())
                    .reportCount(comment.getReportCount())
                    .build();
            comments.add(commentInfo);
        }

        return CommentListResponse.of(comments);
    }
}
