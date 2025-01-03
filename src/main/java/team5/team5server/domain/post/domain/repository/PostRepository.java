package team5.team5server.domain.post.domain.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import team5.team5server.domain.post.domain.Post;
import team5.team5server.domain.post.domain.PostCategory;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPostCategory(PostCategory postCategory, Sort sort);
}
