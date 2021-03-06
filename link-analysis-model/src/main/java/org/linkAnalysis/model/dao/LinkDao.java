package org.linkAnalysis.model.dao;

import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;

/**
 * This interface provides persistence operations for {@link Link} objects.
 * Now it has no specific methods, it has only methods inherited from
 * {@link Dao} interface
 *
 * @author Pavel Karpukhin
 */
public interface LinkDao extends Dao<Link> {

    SearchResult<Link> getLinksByCriteria(LinkSearchCriteria searchCriteria);
}
