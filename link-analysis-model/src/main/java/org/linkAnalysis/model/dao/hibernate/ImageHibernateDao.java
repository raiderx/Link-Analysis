package org.linkAnalysis.model.dao.hibernate;

import org.linkAnalysis.model.dao.ImageDao;
import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.search.ImageSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;

/**
 * Hibernate implementation of LinkDao
 *
 * @author Pavel Karpukhin
 */
public class ImageHibernateDao extends AbstractHibernateDao<Image>
        implements ImageDao {

    @Override
    public SearchResult<Image> getImagesByCriteria(ImageSearchCriteria searchCriteria) {
        ImageQueryBuilder queryBuilder = new ImageQueryBuilder(searchCriteria);

        return queryBuilder.executeQuery(this);
    }
}
