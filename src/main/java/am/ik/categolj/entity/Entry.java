package am.ik.categolj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import am.ik.categolj.serializer.CategorySerializer;
import am.ik.categolj.serializer.DateSerializer;
import am.ik.categolj.util.CategoryUtils;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Transient;

@Entity(value = "entry", noClassnameStored = true)
public class Entry {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
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

    @JsonIgnore
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonSerialize(using = CategorySerializer.class)
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

    @JsonIgnore
    public String getCategoryLink() {
        return CategoryUtils.categoryLinkString(categoriesPath);
    }

    @JsonIgnore
    public String getCategoryBreadCrumb() {
        return CategoryUtils.categoryBreadCrumb(categoriesPath);
    }

    @JsonIgnore
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

    @JsonIgnore
    public List<String> getCategoriesPath() {
        return categoriesPath;
    }

    @JsonIgnore
    public String getDistinctCategory() {
        return distinctCategory;
    }

    public void setDistinctCategory(String distinctCategory) {
        this.distinctCategory = distinctCategory;
    }

    @JsonIgnore
    public List<String> getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(List<String> categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    @JsonIgnore
    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
