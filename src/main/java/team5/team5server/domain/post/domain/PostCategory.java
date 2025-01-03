package team5.team5server.domain.post.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import team5.team5server.global.response.exception.CustomException;
import team5.team5server.global.response.exception.ErrorCode;

@Getter
@Slf4j
public enum PostCategory {
    CLUB("동아리"), MAJOR("단과대");

    private final String category;

    PostCategory(String category) {
        this.category = category;
    }

    public static PostCategory from(String category) {
        log.info("PostCategory: {}", category);
        for (PostCategory postCategory : PostCategory.values()) {
            if (postCategory.getCategory().equals(category)) {
                log.info("Matching category: {}", postCategory);
                return postCategory;
            }
        }
        throw new CustomException(ErrorCode.NO_SUCH_CATEGORY);
    }
}
