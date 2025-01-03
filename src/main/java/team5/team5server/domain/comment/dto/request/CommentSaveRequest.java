package team5.team5server.domain.comment.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequest {

    private Long postId;

    private Long userId;

    private String contents;

    //todo photo 추가

    @Builder
    public CommentSaveRequest(Long postId, Long userId, String contents) {
        this.postId = postId;
        this.userId = userId;
        this.contents = contents;
    }

}