package org.linkAnalysis.model.dao.hibernate;

import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.AbstractSearchCriteria;
import org.linkAnalysis.model.search.LinkSearchCriteria;

/**
 * @author Pavel Karpukhin
 */
public class LinkQueryBuilder extends AbstractQueryBuilder<Link> {

    public LinkQueryBuilder(LinkSearchCriteria searchCriteria) {
        super(Link.class, searchCriteria);
    }
}
