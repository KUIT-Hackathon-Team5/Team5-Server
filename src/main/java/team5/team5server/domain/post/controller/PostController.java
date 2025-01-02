package team5.team5server.domain.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team5.team5server.domain.post.dto.request.PostEditRequest;
import team5.team5server.domain.post.dto.request.PostSaveRequest;
import team5.team5server.domain.post.dto.response.*;
import team5.team5server.domain.post.service.PostService;
import team5.team5server.global.response.BaseResponse;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 작성
     * @param postSaveRequest
     * @return
     */
    @PostMapping
    public BaseResponse<PostSaveResponse> post(@RequestBody final PostSaveRequest postSaveRequest) {

        return null;
    }

    /**
     * 게시글 목록 조회
     * @param category
     * @param type
     * @param order
     * @return
     */
    @GetMapping
    public BaseResponse<PostListResponse> view(
            @RequestParam("category") final String category,
            @RequestParam("type") final String type,
            @RequestParam("order") final int order) {

        return null;
    }

    /**
     * 게시글 상세보기
     * @param postId
     * @return
     */
    @GetMapping("{postId}")
    public BaseResponse<PostInfoResponse> viewInfo(
            @PathVariable Long postId
    ) {

        return null;
    }

    /**
     * 게시글 수정
     * @param postId
     * @param postEditRequest
     * @return
     */
    @PatchMapping("{postId}")
    public BaseResponse<PostEditResponse> edit(
            @PathVariable Long postId, @RequestBody PostEditRequest postEditRequest
    ) {
        return null;
    }

    /**
     * 게시글 삭제
     * @param postId
     * @return
     */
    @DeleteMapping("{postId}")
    public BaseResponse<Void> delete(
            @PathVariable Long postId
    ) {
        return null;
    }

    /**
     * 게시글 신고
     * @param postId
     * @return
     */
    @PostMapping("{postId}/report")
    public BaseResponse<PostReportResponse> report(
            @PathVariable Long postId
    ) {
        return null;
    }
}
