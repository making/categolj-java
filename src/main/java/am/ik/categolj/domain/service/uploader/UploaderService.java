package am.ik.categolj.domain.service.uploader;

import org.springframework.web.multipart.MultipartFile;


public interface UploaderService {
    UploadResponse upload(MultipartFile file);

    UploadResponse getUploadedFilesByPage(int page, int count);

    UploadResponse deleteUplodedFileById(Integer id);
}
