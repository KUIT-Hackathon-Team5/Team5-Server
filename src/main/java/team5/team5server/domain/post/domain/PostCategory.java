package team5.team5server.domain.post.domain;

import lombok.Getter;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

@Getter
public enum PostCategory {
    CLUB("동아리"), MAJOR("단과대");

    private String category;

    PostCategory(String category) {
        this.category = category;
    }

    public static PostCategory from(String category) {
        for (PostCategory postCategory : PostCategory.values()) {
            if (postCategory.getCategory().equals(category)) {
                return postCategory;
            }
        }
        throw new CustomException(ErrorCode.NO_SUCH_CATEGORY);
    }
}
