package team5.team5server.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.user.domain.User;

@Getter
@NoArgsConstructor
public class UserSaveResponse {
    private Long id;

    private UserSaveResponse(Long id) {
        this.id = id;
    }

    public static UserSaveResponse of(User user) {
        return new UserSaveResponse(user.getId());
    }

}

