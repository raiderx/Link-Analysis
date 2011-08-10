package org.linkAnalysis.service;

import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.linkAnalysis.service.util.ServiceResult;

/**
 * This interface has methods which give us more abilities in
 * manipulation {@link Link} persistent entity
 *
 * @author Pavel Karpukhin
 */
public interface LinkService extends EntityService<Link> {

    SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria);

    ServiceResult<Link> create(Link link);

    ServiceResult<Link> analyzeLink(Link link, String method);

    /**
     * Returns array of image urls in document for specified url
     *
     * @param url  document url
     * @return array of image urls
     */
    String[] getImageUrlsForUrl(String url);

}
