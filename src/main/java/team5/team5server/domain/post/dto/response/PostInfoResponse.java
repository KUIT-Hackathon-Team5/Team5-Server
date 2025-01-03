package team5.team5server.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.post.domain.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostInfoResponse {

    private Long postId;

    private Long userId;

    private String title;

    private String contents;

    private String organizer;

    private String organizer_link;

    private String type;

    private String place;

    private String category;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int likeCount;

    private int reportCount;

    private LocalDateTime createdDate;

    @Builder
    public PostInfoResponse(Long postId, Long userId, String title, String contents, String organizer, String organizer_link, String type, String place, String category, LocalDateTime startTime, LocalDateTime endTime, int likeCount, int reportCount, LocalDateTime createdDate) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.organizer = organizer;
        this.organizer_link = organizer_link;
        this.type = type;
        this.place = place;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.likeCount = likeCount;
        this.reportCount = reportCount;
        this.createdDate = createdDate;
    }

    public static PostInfoResponse of(Post post) {
        return PostInfoResponse.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .organizer(post.getOrganizer())
                .organizer_link(post.getOrganizerLink())
                .type(post.getPostType().getType())
                .place(post.getPlace())
                .category(post.getPostCategory().getCategory())
                .startTime(post.getStartTime())
                .endTime(post.getEndTime())
                .likeCount(post.getLikeCount())
                .reportCount(post.getReportCount())
                .createdDate(post.getCreatedAt())
                .build();
    }
}
