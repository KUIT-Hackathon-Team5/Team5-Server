package team5.team5server.domain.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team5.team5server.domain.post.dto.request.PostEditRequest;
import team5.team5server.domain.post.dto.request.PostSaveRequest;
import team5.team5server.domain.post.dto.response.*;
import team5.team5server.domain.post.service.PostService;
import team5.team5server.global.response.BaseResponse;

import java.util.List;

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
    public BaseResponse<PostSaveResponse> post(@RequestBody final PostSaveRequest postSaveRequest,
                                               @RequestParam(value = "fileKeys",  required = false) List<String> fileKeys
                                               ) {
        return BaseResponse.ok(postService.uploadPost(postSaveRequest, fileKeys));
    }

    /**
     * 게시글 목록 정렬
     * @param category
     * @param order
     * @return
     */
    @GetMapping
    public BaseResponse<PostListResponse> view(
            @RequestParam("category") final String category,
            @RequestParam("order") final int order) {

        return BaseResponse.ok(postService.viewPost(category, order));
    }

    /**
     * 게시글 목록 전체 조회
     * @param category
     * @param order
     * @return
     */
    @GetMapping("/view")
    public BaseResponse<PostListResponse> viewAll(
            @RequestParam("category") final String category,
            @RequestParam("order") final int order) {

        return BaseResponse.ok(postService.viewPostAll(category, order));
    }

    /**
     * 게시글 상세보기
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public BaseResponse<PostInfoResponse> viewInfo(
            @PathVariable Long postId
    ) {
        return BaseResponse.ok(postService.viewPostInfo(postId));
    }

    /**
     * 게시글 수정
     * @param postId
     * @param postEditRequest
     * @return
     */
    @PatchMapping("/{postId}")
    public BaseResponse<PostEditResponse> edit(
            @PathVariable Long postId, @RequestBody PostEditRequest postEditRequest
    ) {
        return BaseResponse.ok(postService.editPost(postId, postEditRequest));
    }


}
