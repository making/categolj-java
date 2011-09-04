package am.ik.categolj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import am.ik.categolj.util.CategoryUtils;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Transient;

@Entity(value = "entry", noClassnameStored = true)
public class Entry {
    private Long id;
    @Size(min = 1)
    private String title;
    @Size(min = 1)
    private String content;
    @NotNull
    @Property("created-at")
    private Date createdAt;
    @NotNull
    @Property("updated-at")
    private Date updatedAt;
    @Transient
    private boolean updateDate = false;
    @Property("category")
    @Size(min = 1)
    private List<String> categoriesPath;
    @Property("distinct-category")
    private String distinctCategory;
    @Property("category-index")
    private List<String> categoryIndex;
    private Set<String> keywords;

    public Entry() {
    }

    public Entry(Long id, String title, String content, Date createdAt,
            Date updatedAt, List<Category> category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        setCategory(category);
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
        return CategoryUtils.populateCategoriesFromPath(categoriesPath);
    }

    public void setCategory(List<Category> category) {
        List<String> paths = new ArrayList<String>();
        for (Category c : category) {
            paths.add(c.getName());
        }
        this.categoriesPath = paths;
    }

    public String getCategoryLink() {
        return CategoryUtils.categoryLinkString(categoriesPath);
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Entry [id=" + id + ", title=" + title + ", content="
                + (content == null ? 0 : content.length()) + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt;
    }

    public void setCategoriesPath(List<String> categoriesPath) {
        this.categoriesPath = categoriesPath;
    }

    public List<String> getCategoriesPath() {
        return categoriesPath;
    }

    public String getDistinctCategory() {
        return distinctCategory;
    }

    public void setDistinctCategory(String distinctCategory) {
        this.distinctCategory = distinctCategory;
    }

    public List<String> getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(List<String> categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
