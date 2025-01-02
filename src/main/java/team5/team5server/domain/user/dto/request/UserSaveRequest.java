package team5.team5server.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequest {

    @NotBlank(message = "이메일 입력은 필수입니다. 최소 6자 이상입니다.")
    @Size(min = 6)
    private String email;

    @NotNull(message = "비밀번호 입력은 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$", message = "비밀번호는 영어와 숫자를 포함해서 8자 이상 16자 이내로 입력해주세요.")
    private String password;

    @Builder
    public UserSaveRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
