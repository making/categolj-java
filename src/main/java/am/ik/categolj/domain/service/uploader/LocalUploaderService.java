package am.ik.categolj.domain.service.uploader;

import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import am.ik.categolj.domain.common.consts.LogId;
import am.ik.yalf.logger.Logger;

@Service
public class LocalUploaderService implements UploaderService, InitializingBean {

    @Value("${categolj.upload.dir}")
    protected Resource uploadDir;
    protected int fileIdDigits = 5;

    private static final Logger logger = Logger
            .getLogger(LocalUploaderService.class);

    protected final ConcurrentMap<Integer, FileInfo> fileMap = new ConcurrentHashMap<Integer, FileInfo>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(uploadDir);
        File dir = uploadDir.getFile();
        if (!dir.exists()) {
            throw new RuntimeException(dir + " is not exists!");
        }
        if (logger.isInfoEnabled()) {
            logger.info(LogId.ICTGL007, dir, dir.getAbsoluteFile());
        }
        for (File d : dir.listFiles()) {
            if (d.isDirectory()) {
                for (File f : d.listFiles()) {
                    FileInfo info = new FileInfo(f);
                    logger.debug(LogId.DCTGL011, info);
                    fileMap.put(info.getId(), info);
                }
            }
        }
    }

    public void setUploadDir(Resource uploadDir) {
        this.uploadDir = uploadDir;
    }

    @Override
    public UploadResponse upload(MultipartFile file) {
        logger.debug(LogId.DCTGL012, file);

        UploadResponse response = new UploadResponse();
        try {

            int id = 0;
            for (FileInfo f : fileMap.values()) {
                if (f.getId() > id) {
                    id = f.getId().intValue();
                }
            }
            id++;

            File dest = new File(String.format("%s/%0" + fileIdDigits + "d/%s",
                    uploadDir.getFile(), id, file.getOriginalFilename()));
            logger.info(LogId.ICTGL005, dest.getAbsolutePath());
            FileUtils.forceMkdir(dest.getParentFile());
            FileUtils.writeByteArrayToFile(dest, file.getBytes());

            FileInfo info = new FileInfo(dest);
            info.setId(id);
            response.setRes(UploadResponse.OK);
            response.setId(id);
            response.setFile(info);
            response.setUploadDir(uploadDir.getFilename());
            fileMap.put(id, info);
        } catch (Throwable e) {
            response.setRes(UploadResponse.NG);
            logger.error(LogId.ECTGL001, e, file);
        }
        logger.debug(LogId.DCTGL013, response);
        return response;
    }

    @Override
    public UploadResponse getUploadedFilesByPage(int page, int count) {
        // page and count are not used now.
        UploadResponse response = new UploadResponse();
        try {
            SortedSet<FileInfo> files = new TreeSet<FileInfo>(fileMap.values());
            response.setFiles(files);
            response.setRes(UploadResponse.OK);
            response.setUploadDir(uploadDir.getFilename());
        } catch (Throwable e) {
            response.setRes(UploadResponse.NG);
            logger.error(LogId.ECTGL002, e, page, count);
        }
        logger.debug(LogId.DCTGL013, response);
        return response;
    }

    @Override
    public UploadResponse deleteUplodedFileById(Integer id) {
        UploadResponse response = new UploadResponse();
        try {
            FileInfo f = fileMap.get(id);
            if (f != null) {
                response.setRes(UploadResponse.OK);
                response.setId(id);
                File target = new File(uploadDir.getFile(), f.getFileName());
                logger.info(LogId.ICTGL006, target);
                FileUtils.forceDelete(target.getParentFile());
                fileMap.remove(id);
            } else {
                response.setRes(UploadResponse.NG);
            }
        } catch (Throwable e) {
            response.setRes(UploadResponse.NG);
            logger.error(LogId.ECTGL003, e, id);
        }
        logger.debug(LogId.DCTGL013, response);
        return response;
    }
}
