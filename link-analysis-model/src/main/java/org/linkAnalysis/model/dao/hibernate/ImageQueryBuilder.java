package org.linkAnalysis.model.dao.hibernate;

import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.search.ImageSearchCriteria;

/**
 * @author Pavel Karpukhin
 */
public class ImageQueryBuilder extends AbstractQueryBuilder<Image> {

    public ImageQueryBuilder(ImageSearchCriteria searchCriteria) {
        super(Image.class, searchCriteria);
    }
}
