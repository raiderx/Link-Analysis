package org.linkAnalysis.model.dao.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.linkAnalysis.model.entity.DomainEntity;
import org.linkAnalysis.model.search.AbstractSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class AbstractQueryBuilder<T extends DomainEntity> {

    protected Class<T> clazz;
    protected AbstractSearchCriteria<T> searchCriteria;

    public AbstractQueryBuilder(Class<T> clazz, AbstractSearchCriteria<T> searchCriteria) {
        this.clazz = clazz;
        this.searchCriteria = searchCriteria;
    }

    public SearchResult<T> executeQuery(AbstractHibernateDao<T> dao) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        applyCriteria(criteria);

        List<T> results = criteria.getExecutableCriteria(dao.getSession()).list();
        int pageCount = getPageCount(dao);

        return new SearchResult<T>(pageCount, results);
    }

    protected void applyCriteria(DetachedCriteria criteria) {
        if (searchCriteria.getExample() != null) {
            applyExample(criteria);
        }
        prepare(criteria);
    }

    protected int getPageCount(AbstractHibernateDao<T> dao) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        applyCriteria(criteria);

        criteria.setProjection(Projections.count("id"));
        List<Long> count = criteria.getExecutableCriteria(dao.getSession()).list();

        int resultsPerPage = searchCriteria.getResultsPerPage();
        int totalResults = count.get(0).intValue();
        int lastPage = totalResults % resultsPerPage > 0 ? 1 : 0;
        if (totalResults == 0) {
            lastPage = 1;
        }

        return totalResults / resultsPerPage + lastPage;
    }

    protected void applyExample(DetachedCriteria criteria) {
        Example example = Example.create(searchCriteria.getExample())
                .enableLike(MatchMode.ANYWHERE)
                .ignoreCase();

        criteria.add(example);
    }

    protected void prepare(DetachedCriteria criteria) {
        // To be overriden in subclass
    }
}
