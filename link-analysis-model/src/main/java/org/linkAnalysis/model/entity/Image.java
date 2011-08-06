package org.linkAnalysis.model.entity;

/**
 * @author Pavel Karpukhin
 */
public class Image extends DomainEntity {

    private Link link;
    private String url;
    private Boolean downloaded;
    private String mimeType;
    private Integer size;
    private Integer Height;
    private Integer Width;
    private String name;
    private String extension;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
