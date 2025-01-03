package team5.team5server.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationResponse {
    private boolean verified;

    private VerificationResponse(boolean verified) {
        this.verified = verified;
    }

    public static VerificationResponse of(boolean verified) {
        return new VerificationResponse(verified);
    }
}
