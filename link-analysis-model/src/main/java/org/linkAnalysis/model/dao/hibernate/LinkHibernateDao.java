package org.linkAnalysis.model.dao.hibernate;

import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;

/**
 * Hibernate implementation of LinkDao
 *
 * @author Pavel Karpukhin
 */
public class LinkHibernateDao extends AbstractHibernateDao<Link>
        implements LinkDao {

    @Override
    public SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria) {
        LinkQueryBuilder queryBuilder = new LinkQueryBuilder(searchCriteria);

        return queryBuilder.executeQuery(this);
    }
}
