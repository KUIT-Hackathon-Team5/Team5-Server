package team5.team5server.domain.post.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team5.team5server.domain.post.domain.Post;

@Getter
@NoArgsConstructor
public class PostSaveResponse {

    private Long postId;

    public PostSaveResponse(Long postId) {
        this.postId = postId;
    }

    public static PostSaveResponse of(Post post) {
        return new PostSaveResponse(post.getId());
    }
}
