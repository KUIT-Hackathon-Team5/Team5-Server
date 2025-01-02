package team5.team5server.domain.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team5.team5server.domain.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
