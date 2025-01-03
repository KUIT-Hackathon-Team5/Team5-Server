package team5.team5server.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostInfo {
    private Long postId;

    private Long userId;

    private String title;

    private String contents;

    private String place;

    //todo image 필드 추가

    private String type;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int reportCount;

    private LocalDateTime createdDate;

    @Builder
    public PostInfo(Long postId, Long userId, String title, String contents, String place, String type, LocalDateTime startTime, LocalDateTime endTime, int reportCount, LocalDateTime createdDate) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.place = place;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reportCount = reportCount;
        this.createdDate = createdDate;
    }
}
