package team5.team5server.domain.image.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageUploadResponse {

    private List<String> imageKeys;

    public ImageUploadResponse(List<String> imageKeys) {
        this.imageKeys = imageKeys;
    }

    public static ImageUploadResponse of(List<String> imageKeys) {
        return new ImageUploadResponse(imageKeys);
    }
}
