package team5.team5server.domain.post.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.post.domain.Post;

@Getter
@NoArgsConstructor
public class PostEditResponse {

    private Long postId;

    public PostEditResponse(Long postId) {
        this.postId = postId;
    }

    public static PostEditResponse of(Post post) {
        return new PostEditResponse(post.getId());
    }
}
