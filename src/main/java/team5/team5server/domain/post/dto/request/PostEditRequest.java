package team5.team5server.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostEditRequest {
    private String title;

    private String contents;

    private String organizer;

    private String place;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public PostEditRequest(String title, String contents, String organizer, String place, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.contents = contents;
        this.organizer = organizer;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
