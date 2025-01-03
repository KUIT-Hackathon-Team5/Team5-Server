package team5.team5server.domain.comment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.domain.user.domain.User;
import team5.team5server.global.entity.BaseEntity;
import team5.team5server.global.entity.EntityStatus;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private String contents;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Column(nullable = false)
    private int reportCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EntityStatus entityStatus;
}
