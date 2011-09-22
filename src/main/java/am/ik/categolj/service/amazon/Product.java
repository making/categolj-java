package am.ik.categolj.service.amazon;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable {
    private String asin;
    private String title;
    private String link;
    private String imageLink;
    private List<String> authors;
    private String publicationDate;

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Product [asin=" + asin + ", title=" + title + ", link=" + link
                + ", imageLink=" + imageLink + ", authors=" + authors
                + ", publicationDate=" + publicationDate + "]";
    }

}
