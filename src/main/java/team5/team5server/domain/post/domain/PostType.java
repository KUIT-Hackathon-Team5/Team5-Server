package team5.team5server.domain.post.domain;

import lombok.Getter;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

@Getter
public enum PostType {
    SPORTS("스포츠"), ARTS("예술"), GENERAL("일반");

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
