package team5.team5server.domain.comment.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentEditRequest {
    private String contents;

    @Builder
    public CommentEditRequest(String contents) {
        this.contents = contents;
    }
}
