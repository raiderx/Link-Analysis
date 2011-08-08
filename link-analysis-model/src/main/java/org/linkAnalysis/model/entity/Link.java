package org.linkAnalysis.model.entity;

/**
 * @author Pavel Karpukhin
 */
public class Link extends DomainEntity {

    private String url;

    /**
     * Returns url of the link
     *
     * @return url of the link
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url of the link
     *
     * @param url  url of the link
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
