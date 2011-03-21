package am.ik.categolj.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import am.ik.categolj.util.CategoryUtils;

public class Entry {
    public Long id;
    @Size(min = 1)
    public String title;
    @Size(min = 1)
    public String content;
    @NotNull
    public Date createdAt;
    @NotNull
    public Date updatedAt;
    @Size(min = 1)
    public List<Category> category;

    public boolean updateDate = false;

    public Entry() {
    }

    public Entry(Long id, String title, String content, Date createdAt,
            Date updatedAt, List<Category> category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

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

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getCategoryLink() {
        return CategoryUtils.categoryLinkString(category);
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Entry [id=" + id + ", title=" + title + ", content=" + content
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", category=" + category + "]";
    }
}
