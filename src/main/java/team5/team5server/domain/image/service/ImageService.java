package team5.team5server.domain.image.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team5.team5server.domain.image.dto.response.ImageUploadResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final FileStore fileStore;


    public ImageUploadResponse saveImages(List<MultipartFile> form) throws IOException {
        List<String> keys = new ArrayList<>();
        for (MultipartFile multipartFile : form) {
            keys.add(fileStore.storeFile(multipartFile));
        }

        return ImageUploadResponse.of(keys);
    }
}
