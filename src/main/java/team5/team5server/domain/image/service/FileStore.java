package team5.team5server.domain.image.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.upload-path}")
    private String fileDir; //설정해둔 경로

    private final String domain = "http://ec2-3-39-86-18.ap-northeast-2.compute.amazonaws.com:8080";


    public String storeFile(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) { //파일이 존재한다면
            //원본파일 이름을 가져와
            String originalFilename = multipartFile.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();
            String ext = extracted(originalFilename);
            //UUID와 확장명을 합친다
            String storeFileName = uuid + "." + ext;
            //설정해둔 저장소에 저장
            multipartFile.transferTo(new File(getFullPath(storeFileName)));

            return storeFileName;
        }
        throw new MultipartException("multipartFile is Null");
    }
    public String getFullPath(String filename) {
        return fileDir + filename;
    }


    private String extracted(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


    public String getPublicUrl(String filePath) {
        return domain + fileDir + filePath;
    }
}
