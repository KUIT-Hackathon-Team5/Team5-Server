package team5.team5server.domain.comment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team5.team5server.domain.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
