package team5.team5server.domain.post.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team5.team5server.domain.image.domain.Image;
import team5.team5server.domain.user.domain.User;
import team5.team5server.global.entity.BaseEntity;
import team5.team5server.global.entity.EntityStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //todo Image 추가하기
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 100)
    private String place;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type", nullable = false, length = 50)
    private PostType postType; //스포츠, 예술 등등

    @Enumerated(EnumType.STRING)
    @Column(name = "post_category", nullable = false)
    private PostCategory postCategory; //단과대, 동아리

    @Column(length = 200, nullable = false)
    private String organizer;

    @Column(name = "organizer_link", length = 255)
    private String organizerLink;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime; //2007-12-03T10:15:30 형식

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "report_count", nullable = false)
    private int reportCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EntityStatus entityStatus;

    @Builder
    public Post(User user, String title, String contents, String place, PostType postType, PostCategory postCategory, String organizer, String organizerLink, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.place = place;
        this.postType = postType;
        this.postCategory = postCategory;
        this.organizer = organizer;
        this.organizerLink = organizerLink;
        this.startTime = startTime;
        this.endTime = endTime;
        this.viewCount = 0;
        this.reportCount = 0;
        this.entityStatus = EntityStatus.ACTIVE;
    }

}
