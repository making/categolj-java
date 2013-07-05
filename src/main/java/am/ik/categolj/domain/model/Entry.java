package am.ik.categolj.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import am.ik.categolj.app.common.util.CategoryUtils;
import am.ik.categolj.domain.common.serializer.CategorySerializer;
import am.ik.categolj.domain.common.serializer.DateSerializer;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;

@Entity(value = "entry", noClassnameStored = true)
public class Entry {
    private Long id;
    private String title;
    private String content;
    @Property("created-at")
    private Date createdAt;
    @Property("updated-at")
    private Date updatedAt;
    @Property("category")
    private List<String> category;
    @Property("distinct-category")
    private String distinctCategory;
    @Property("category-index")
    private List<String> categoryIndex;
    private Set<String> keywords;

    public Entry() {
    }

    public Entry(Long id, String title, String content, Date createdAt,
            Date updatedAt, List<String> category) {
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

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @JsonSerialize(using = CategorySerializer.class)
    public List<String> getCategory() {
        return category;
    }

    @JsonIgnore
    public List<Category> getCategories() {
        return CategoryUtils.populateCategoriesFromPath(category);
    }

    @JsonIgnore
    public String getCategoryLink() {
        return CategoryUtils.categoryLinkString(category);
    }

    @JsonIgnore
    public String getCategoryBreadCrumb() {
        return CategoryUtils.categoryBreadCrumb(category);
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
    //
    // @Override
    // public String toString() {
    // return "Entry [id=" + id + ", title=" + title + ", content="
    // + (content == null ? 0 : content.length()) + ", createdAt="
    // + createdAt + ", updatedAt=" + updatedAt + ", category="
    // + category + "]";
    // }
}
