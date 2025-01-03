package team5.team5server.domain.user.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//record 한번 써본 거..
public record UserLoginResponse(String token, Long userId) {

    public static UserLoginResponse of(String token, Long userId) {
        return new UserLoginResponse(token, userId);
    }
}
