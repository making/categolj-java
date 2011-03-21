package am.ik.categolj.service.uploader;

import java.io.File;

public class FileInfo implements Comparable<FileInfo> {
    private Integer id;
    private String fileName;
    private String ext;
    private Long size;

    public FileInfo() {
    }

    public FileInfo(File file) {
        File d = file.getParentFile();
        Integer id = Integer.valueOf(d.getName());
        setFileName(d.getName() + "/" + file.getName());
        setId(id);
        setSize(file.length());
        String[] fileString = file.getName().split("\\.");
        setExt(fileString[fileString.length - 1]);
    }

    public FileInfo(Integer id, String fileName, String ext, Long size) {
        super();
        this.id = id;
        this.fileName = fileName;
        this.ext = ext;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileInfo [id=" + id + ", fileName=" + fileName + ", ext=" + ext
                + ", size=" + size + "]";
    }

    @Override
    public int compareTo(FileInfo o) {
        int diff = this.id - o.id;
        if (diff != 0) {
            return diff > 0 ? 1 : -1;
        } else {
            return 0;
        }
    }
}
