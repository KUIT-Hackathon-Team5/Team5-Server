package team5.team5server.domain.comment.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.comment.domain.Comment;

@Getter
@NoArgsConstructor
public class CommentEditResponse {
    private Long commentId;

    public CommentEditResponse(Long commentId) {
        this.commentId = commentId;
    }

    public static CommentEditResponse of(Comment comment) {
        return new CommentEditResponse(comment.getId());
    }
}
