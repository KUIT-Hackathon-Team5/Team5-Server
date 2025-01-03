package team5.team5server.domain.user.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team5.team5server.domain.user.domain.User;
import team5.team5server.domain.user.domain.repository.UserRepository;
import team5.team5server.domain.user.dto.request.EmailSendRequest;
import team5.team5server.domain.user.dto.request.UserLoginRequest;
import team5.team5server.domain.user.dto.request.UserSaveRequest;
import team5.team5server.domain.user.dto.response.VerificationResponse;
import team5.team5server.domain.user.dto.response.UserLoginResponse;
import team5.team5server.domain.user.dto.response.UserSaveResponse;
import team5.team5server.global.config.RedisService;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final String AUTH_CODE_PREFIX = "AuthCode ";

    private final UserRepository userRepository;

    private final MailService mailService;

    private final RedisService redisService;
    private final JwtUtil jwtUtil;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;


    public void sendCodeToEmail(EmailSendRequest emailSendRequest) {
        String toEmail = emailSendRequest.getEmail();
        this.checkDuplicatedEmail(toEmail);

        final String title = "KU:zone 이메일 인증 번호";
        String authCode = this.createCode();
        log.debug("Auth Code: {}", authCode);
        mailService.sendEmail(toEmail, title, authCode);
        log.debug("Sending Email to {}", toEmail);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )

        log.debug("Redis 저장 시작");

        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.authCodeExpirationMillis));

        log.debug("Redis 저장 완료");
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            log.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createCode() exception occur");
            throw new CustomException(ErrorCode.NO_SUCH_ALGORITHM);
        }
    }

    public VerificationResponse verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);

        return VerificationResponse.of(authResult);
    }

    @Transactional
    public UserSaveResponse saveUser(@Valid final UserSaveRequest userSaveRequest) {
        this.checkDuplicatedEmail(userSaveRequest.getEmail());
        this.checkDuplicateName(userSaveRequest.getName());

        User user = User.builder()
                .email(userSaveRequest.getEmail())
                .password(userSaveRequest.getPassword())
                .name(userSaveRequest.getName())
                .build();

        userRepository.save(user);
        return UserSaveResponse.of(user);
    }

    private void checkDuplicateName(String name) {
        if (userRepository.existsByName(name)) {
            log.debug("MemberServiceImpl.checkDuplicateName exception occur name: {}", name);
            throw new CustomException(ErrorCode.DUPLICATE_NAME);
        }
    }


    public UserLoginResponse login(@Valid UserLoginRequest userLoginRequest) {

        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        String token = jwtUtil.generateAccessToken(user.getEmail());
        log.debug("generating access token: {}", token);

        return UserLoginResponse.of(token, user.getId());
    }

    public VerificationResponse checkName(@Valid UserSaveRequest userSaveRequest) {
        this.checkDuplicateName(userSaveRequest.getName());
        return VerificationResponse.of(true);
    }
}
