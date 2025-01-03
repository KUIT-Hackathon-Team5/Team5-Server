package team5.team5server.domain.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team5.team5server.domain.image.dto.response.ImageUploadResponse;
import team5.team5server.domain.image.service.ImageService;
import team5.team5server.global.response.BaseResponse;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public BaseResponse<ImageUploadResponse> uploadImages(@RequestParam("file") List<MultipartFile> form) throws IOException {
        return BaseResponse.ok(imageService.saveImages(form));
    }
}
