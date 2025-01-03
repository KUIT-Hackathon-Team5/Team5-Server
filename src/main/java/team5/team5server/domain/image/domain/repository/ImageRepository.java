package team5.team5server.domain.image.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team5.team5server.domain.image.domain.Image;
import team5.team5server.domain.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPost(Post post);
}
