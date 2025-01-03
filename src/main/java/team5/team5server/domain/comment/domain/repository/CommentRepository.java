package team5.team5server.domain.comment.domain.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import team5.team5server.domain.comment.domain.Comment;
import team5.team5server.domain.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post, Sort sort);
}
