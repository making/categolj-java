package am.ik.categolj.app.model.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class EntryForm {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date updatedAt;
    private boolean updateDate = false;
    @NotNull
    @Size(min = 1)
    private List<String> category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "EntryForm [id=" + id + ", title=" + title + ", content="
                + (content == null ? 0 : content.length())  + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", updateDate=" + updateDate + ", category="
                + category + "]";
    }


}
