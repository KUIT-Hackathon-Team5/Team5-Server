package team5.team5server.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team5.team5server.domain.user.dto.request.CheckNameRequest;
import team5.team5server.domain.user.dto.request.EmailSendRequest;
import team5.team5server.domain.user.dto.request.UserLoginRequest;
import team5.team5server.domain.user.dto.request.UserSaveRequest;
import team5.team5server.domain.user.dto.response.UserLoginResponse;
import team5.team5server.domain.user.dto.response.UserSaveResponse;
import team5.team5server.domain.user.service.UserService;
import team5.team5server.domain.user.dto.response.VerificationResponse;
import team5.team5server.global.response.BaseResponse;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 인증코드 발송 요청
     * @param emailSendRequest
     * @return
     */
    @PostMapping("/emails/verification-requests")
    public BaseResponse<Void> sendMessage(@RequestBody @Valid final EmailSendRequest emailSendRequest) {
        userService.sendCodeToEmail(emailSendRequest);

        return BaseResponse.ok(null);
    }

    /**
     * 이메일 인증 요청
     * @param email
     * @param authCode
     * @return
     */
    @GetMapping("/emails/verifications")
    public BaseResponse<VerificationResponse> verificationEmail(@RequestParam("email") final String email,
                                                                @RequestParam("code") String authCode) {

        return BaseResponse.ok(userService.verifiedCode(email, authCode));

    }

    /**
     * 회원가입
     * @param userSaveRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<UserSaveResponse> register(@RequestBody @Valid final UserSaveRequest userSaveRequest) {
        return BaseResponse.ok(userService.saveUser(userSaveRequest));
    }

    @PostMapping("/name")
    public BaseResponse<VerificationResponse> check(@RequestBody @Valid final CheckNameRequest checkNameRequest) {
        return BaseResponse.ok(userService.checkName(checkNameRequest));
    }

    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> login(@RequestBody @Valid final UserLoginRequest userLoginRequest) {
        return BaseResponse.ok(userService.login(userLoginRequest));
    }

}
