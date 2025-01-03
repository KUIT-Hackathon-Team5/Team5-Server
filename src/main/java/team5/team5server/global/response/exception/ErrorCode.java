package team5.team5server.global.response.exception;

public enum ErrorCode {
    INVALID_EMAIL_OR_PASSWORD(401, "일치하지 않는 이메일과 비밀번호입니다."),
    MISSING_AUTH_HEADER(401, "Authorization 헤더를 찾을 수 없습니다."),
    INVALID_ACCESS_TOKEN(401, "적절하지 않거나 만료된 토큰입니다."),
//    INVALID_REFRESH_TOKEN(402, "Invalid or expired refresh token"),
    FORBIDDEN_ACCESS(403, "제한된 접근입니다."),

    DUPLICATE_EMAIL(404, "이미 존재하는 이메일입니다."),
    DUPLICATE_NAME(301, "이미 존재하는 닉네임입니다."),


    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    EMAIL_SEND_EXCEPTION(500, "메일을 전송하는데 실패하였습니다."),
    NO_SUCH_ALGORITHM(500, "메일 인증 코드 생성시 오류가 발생했습니다."),

    NO_SUCH_USER(500, "알맞은 사용자를 찾을 수 없습니다."),
    NO_SUCH_POST(500, "알맞은 게시글을 찾을 수 없습니다."),
    NO_SUCH_CATEGORY(500, "알맞은 카테고리를 찾을 수 없습니다"),
    NO_SUCH_TYPE(500, "알맞은 분야를 찾을 수 없습니다."),
    NO_SUCH_COMMENT(500, "알맞은 댓글을 찾을 수 없습니다."),

    NO_SUCH_ORDER(500,"올바른 정렬 순서가 아닙니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

