package team5.team5server.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendRequest {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "올바른 이메일 형식을 입력해주세요!")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@konkuk\\.ac\\.kr$",
            message = "건국대학교 이메일을 사용해주세요!")
    private String email;
}
