package team5.team5server.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.post.domain.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    private int reportCount;

    private LocalDateTime createdDate;

    private List<String> imageUrls;

    @Builder
    public PostInfoResponse(Long postId, Long userId, String title, String contents, String organizer, String organizer_link, String type, String place, String category, LocalDateTime startTime, LocalDateTime endTime, int reportCount, LocalDateTime createdDate, List<String> imageUrls) {
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
        this.reportCount = reportCount;
        this.createdDate = createdDate;
        this.imageUrls = imageUrls;
    }

    public static PostInfoResponse of(Post post, List<String> imageUrls) {
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
                .reportCount(post.getReportCount())
                .createdDate(post.getCreatedAt())
                .imageUrls(imageUrls)
                .build();
    }
}
