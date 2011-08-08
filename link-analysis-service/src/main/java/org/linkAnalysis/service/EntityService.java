package org.linkAnalysis.service;

import org.linkAnalysis.model.entity.DomainEntity;

/**
 * This is generic interface for services which would interact with database
 * entities via DAO object.
 * This interface includes all base method declaration which straightly based
 * on database CRUD operations.
 *
 * @author Pavel Karpukhin
 */
public interface EntityService<T extends DomainEntity> {

    /**
     * Returns the persistent instance with the given identifier or null if
     * there is no such persistent instance.
     *
     * @param id an identifier
     * @return a persistent instance or null
     */
    T get(int id);
}
