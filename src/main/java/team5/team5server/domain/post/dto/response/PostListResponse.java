package team5.team5server.domain.post.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostListResponse {

    private List<PostInfo> posts;
    private List<PostInfo> popularPosts;

    public PostListResponse(List<PostInfo> posts) {
        this.posts = posts;
    }

    public PostListResponse(List<PostInfo> posts, List<PostInfo> popularPosts) {
        this.posts = posts;
        this.popularPosts = popularPosts;
    }

    public static PostListResponse of(List<PostInfo> posts) {
        return new PostListResponse(posts);
    }

    public static PostListResponse of(List<PostInfo> posts, List<PostInfo> popularPosts) {
        return new PostListResponse(posts, popularPosts);
    }
}

