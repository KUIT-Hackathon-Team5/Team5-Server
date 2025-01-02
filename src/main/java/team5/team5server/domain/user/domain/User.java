package team5.team5server.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.global.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String email;

    @Column(length = 128)
    private String password;

    @Builder
    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User of(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
