package team5.team5server.domain.comment.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentInfo {

    private Long commentId;

    private Long postId;

    private Long userId;

    private String userName;

    private String contents;

    private LocalDateTime createdDate;

    private int reportCount;

    @Builder
    public CommentInfo(Long commentId, Long postId, Long userId, String userName, String contents, LocalDateTime createdDate, int reportCount) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.createdDate = createdDate;
        this.reportCount = reportCount;
    }
}
