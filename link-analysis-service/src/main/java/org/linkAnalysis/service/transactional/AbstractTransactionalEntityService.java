package org.linkAnalysis.service.transactional;

import org.linkAnalysis.model.dao.Dao;
import org.linkAnalysis.model.entity.DomainEntity;
import org.linkAnalysis.service.EntityService;

/**
 * @author Pavel Karpukhin
 */
public abstract class AbstractTransactionalEntityService<T extends DomainEntity, Y extends Dao<T>>
        implements EntityService<T> {

    protected Y dao;

    @Override
    public T get(int id) {
        return dao.get(id);
    }
}
