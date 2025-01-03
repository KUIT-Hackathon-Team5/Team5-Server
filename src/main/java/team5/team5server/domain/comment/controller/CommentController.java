package team5.team5server.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team5.team5server.domain.comment.dto.request.CommentEditRequest;
import team5.team5server.domain.comment.dto.request.CommentSaveRequest;
import team5.team5server.domain.comment.dto.response.*;
import team5.team5server.domain.comment.service.CommentService;
import team5.team5server.global.response.BaseResponse;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public BaseResponse<CommentSaveResponse> comment(@RequestBody final CommentSaveRequest commentSaveRequest) {
        return BaseResponse.ok(commentService.uploadComment(commentSaveRequest));
    }

    @GetMapping
    public BaseResponse<CommentListResponse> view(@RequestParam("postId") final Long postId) {
        return BaseResponse.ok(commentService.viewComment(postId));
    }

    @PatchMapping("{commentId}")
    public BaseResponse<CommentEditResponse> edit(
            @PathVariable Long commentId, @RequestBody CommentEditRequest commentEditRequest
    ) {
        return null;
    }

    @DeleteMapping("{commentId}")
    public BaseResponse<CommentDeleteResponse> delete(
            @PathVariable Long commentId
    ) {
        return null;
    }

    @PostMapping("{commentId}/report")
    public BaseResponse<CommentReportResponse> report(
            @PathVariable Long commentId
    ) {
        return null;
    }
}
