package team5.team5server.domain.image.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.global.entity.BaseEntity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "images")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Builder
    public Image(Post post, String filePath) {
        this.post = post;
        this.filePath = filePath;
    }
}
