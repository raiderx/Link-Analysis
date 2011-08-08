package org.linkAnalysis.service.transactional;

import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.linkAnalysis.service.LinkService;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class TransactionalLinkService
        extends AbstractTransactionalEntityService<Link, LinkDao>
        implements LinkService {

    public TransactionalLinkService(LinkDao linkDao) {
        this.dao = linkDao;
    }

    @Override
    public String[] getImageUrlsForUrl(String url) {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Link> getAll() {
        return null;
    }

    @Override
    public SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria) {
        return dao.getLinksByCriteria(searchCriteria);
    }
}
