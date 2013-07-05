package am.ik.categolj.domain.service.uploader;

import java.util.Set;

public class UploadResponse {
    public static final String OK = "ok";
    public static final String NG = "ng";

    private String res;
    private Integer id;
    private Set<FileInfo> files;
    private FileInfo file;
    private String uploadDir;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Set<FileInfo> getFiles() {
        return files;
    }

    public void setFiles(Set<FileInfo> files) {
        this.files = files;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFile(FileInfo file) {
        this.file = file;
    }

    public FileInfo getFile() {
        return file;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    @Override
    public String toString() {
        return "UploadResponse [res=" + res + ", id=" + id + ", files=" + files
                + ", file=" + file + ", uploadDir=" + uploadDir + "]";
    }
}
