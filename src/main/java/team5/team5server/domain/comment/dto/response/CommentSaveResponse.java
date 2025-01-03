package team5.team5server.domain.comment.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.comment.domain.Comment;

@Getter
@NoArgsConstructor
public class CommentSaveResponse {

    private Long commendId;

    public CommentSaveResponse(Long commendId) {
        this.commendId = commendId;
    }

    public static CommentSaveResponse of(Comment comment) {
        return new CommentSaveResponse(comment.getId());
    }
}
