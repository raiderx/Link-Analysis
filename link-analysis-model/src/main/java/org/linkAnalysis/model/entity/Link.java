package org.linkAnalysis.model.entity;

/**
 * @author Pavel Karpukhin
 */
public class Link extends DomainEntity {

    private String url;

    /**
     * Returns the url of the link
     *
     * @return the url of the link
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url of the link
     *
     * @param url  link url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
