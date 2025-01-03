package team5.team5server.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckNameRequest {
    //todo 닉네임 4~12 한글 제한
    @NotNull(message = "닉네임을 입력해주세요.")
    @Size(min=4, message = "닉네임은 최소 4자 이상입니다.")
    private String name;

    @Builder
    public CheckNameRequest(String name) {
        this.name = name;
    }
}
