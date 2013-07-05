package am.ik.categolj.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "link", noClassnameStored = true)
public class Link {
    @Id
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String url;
    private Integer order = DEFAULT_ORDER;
    private static final Integer DEFAULT_ORDER = 4;

    public Link() {
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Link [id=" + id + ", name=" + name + ", url=" + url
                + ", order=" + order + "]";
    }

}
