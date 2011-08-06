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
    private Integer height;
    private Integer width;
    private String name;
    private String extension;

    /**
     * Returns the link of the image
     *
     * @return the link of the image
     */
    public Link getLink() {
        return link;
    }

    /**
     * Sets the link of the image
     *
     * @param link
     */
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * Returns url of the image
     *
     * @return url of the image
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url of the image
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
