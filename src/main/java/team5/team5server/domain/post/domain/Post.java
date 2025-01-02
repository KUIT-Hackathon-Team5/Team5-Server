package team5.team5server.domain.post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.user.domain.User;
import team5.team5server.global.entity.BaseEntity;
import team5.team5server.global.entity.EntityStatus;

import java.time.LocalDateTime;

@Getter
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
    @Column(nullable = false, length = 50)
    private PostCategory category; //단과대, 동아리

    @Column(length = 200)
    private String organizer;

    @Column(name = "organizer_link", length = 255)
    private String organizerLink;

    @Column(name = "count_of_comment", nullable = false)
    private int countOfComment;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime; //2007-12-03T10:15:30 형식

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Column(name = "report_count", nullable = false)
    private int reportCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EntityStatus entityStatus;
}
