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

    /**
     * Returns array of image urls in document for specified url
     *
     * @param url  document url
     * @return array of image urls
     */
    String[] getImageUrlsForUrl(String url);

    /**
     * Returns the list of all links
     *
     * @return list of all links
     */
    List<Link> getAll();

    SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria);
}
