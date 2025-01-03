package team5.team5server.domain.comment.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CommentListResponse {

    private List<CommentInfo> comments;

    public CommentListResponse(List<CommentInfo> comments) {
        this.comments = comments;
    }

    public static CommentListResponse of(List<CommentInfo> comments) {
        return new CommentListResponse(comments);
    }
}
