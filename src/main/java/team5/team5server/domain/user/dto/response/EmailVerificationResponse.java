package team5.team5server.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerificationResponse {
    private boolean verified;

    private EmailVerificationResponse(boolean verified) {
        this.verified = verified;
    }

    public static EmailVerificationResponse of(boolean verified) {
        return new EmailVerificationResponse(verified);
    }
}
