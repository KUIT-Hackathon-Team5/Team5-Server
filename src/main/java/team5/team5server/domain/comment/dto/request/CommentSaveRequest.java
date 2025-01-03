package team5.team5server.domain.comment.dto.request;

import lombok.Builder;

public class CommentSaveRequest {

    private Long postId;

    private Long userId;

    private String content;

    //todo photo 추가

    @Builder
    public CommentSaveRequest(Long postId, Long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

}