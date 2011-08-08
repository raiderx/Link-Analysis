package org.linkAnalysis.model.dao;

import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.search.ImageSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;

/**
 * This interface provides persistence operations for {@link Image} objects.
 *
 * @author Pavel Karpukhin
 */
public interface ImageDao extends Dao<Image> {

    SearchResult<Image> getImagesByCriteria(ImageSearchCriteria searchCriteria);
}
