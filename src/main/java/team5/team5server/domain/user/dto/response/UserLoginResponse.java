package team5.team5server.domain.user.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//record 한번 써본 거..
public record UserLoginResponse(String token) {

    public static UserLoginResponse of(String token) {
        return new UserLoginResponse(token);
    }
}
