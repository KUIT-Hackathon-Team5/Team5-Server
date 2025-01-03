package team5.team5server.domain.post.domain;

import lombok.Getter;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

@Getter
public enum PostType { //부원모집, 이벤트, 정기행사, 공연 및 전시, 홍보
    RECRUIT("부원모집"), EVENT("이벤트"), GENERAL("정기행사"), DISPLAY("공연 및 전시"), ADVERTISE("홍보");

    private String type;

    PostType(String type) {
        this.type = type;
    }

    public static PostType from(String type) {
        for (PostType postType : PostType.values()) {
            if (postType.getType().equals(type)) {
                return postType;
            }
        }
        throw new CustomException(ErrorCode.NO_SUCH_TYPE);
    }
}
