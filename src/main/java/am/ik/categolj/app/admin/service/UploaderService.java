package am.ik.categolj.app.admin.service;

import org.springframework.web.multipart.MultipartFile;

import am.ik.categolj.app.admin.service.uploader.UploadResponse;

public interface UploaderService {
    UploadResponse upload(MultipartFile file);

    UploadResponse getUploadedFilesByPage(int page, int count);

    UploadResponse deleteUplodedFileById(Integer id);
}
