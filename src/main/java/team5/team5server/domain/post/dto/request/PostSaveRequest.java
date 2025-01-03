package team5.team5server.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.post.domain.PostCategory;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostSaveRequest {

    private Long userId;

    private String title;

    private String contents;

    private String organizer;

    private String organizer_link;

    private String place;

    //todo photo 추가

    private String type; //분야

    private String category; //단과대, 동아리

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public PostSaveRequest(Long userId, String title, String contents, String organizer, String organizer_link, String place, String type, String category, LocalDateTime startTime, LocalDateTime endTime) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.organizer = organizer;
        this.organizer_link = organizer_link;
        this.place = place;
        this.type = type;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
