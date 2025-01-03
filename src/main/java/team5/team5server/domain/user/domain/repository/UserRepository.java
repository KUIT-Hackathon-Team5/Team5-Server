package team5.team5server.domain.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.team5server.domain.user.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);

    User findByEmail(String email);
    Optional<User> findById(Long id);
}
