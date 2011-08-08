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

    public Boolean getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
