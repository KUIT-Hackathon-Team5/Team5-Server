package team5.team5server.domain.post.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostListResponse {

    private List<PostInfo> posts;

    public PostListResponse(List<PostInfo> posts) {
        this.posts = posts;
    }

    public static PostListResponse of(List<PostInfo> posts) {
        return new PostListResponse(posts);
    }
}

