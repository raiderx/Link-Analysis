package org.linkAnalysis.model.dao.hibernate;

import org.linkAnalysis.model.dao.ImageDao;
import org.linkAnalysis.model.entity.Image;

/**
 * Hibernate implementation of LinkDao
 *
 * @author Pavel Karpukhin
 */
public class ImageHibernateDao extends AbstractHibernateDao<Image>
        implements ImageDao {
}
