package org.linkAnalysis.service;

import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;

import java.util.List;

/**
 * This interface has methods which give us more abilities in
 * manipulation {@link Link} persistent entity
 *
 * @author Pavel Karpukhin
 */
public interface LinkService extends EntityService<Link> {

    SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria);

    /**
     * Checks if an url is available or not (404)
     *
     * @param url
     * @return
     */
    ServiceResult<Boolean> isUrlAvailable(String url);

    //void create(Link link);

    /**
     * Returns array of image urls in document for specified url
     *
     * @param url  document url
     * @return array of image urls
     */
    String[] getImageUrlsForUrl(String url);

}
