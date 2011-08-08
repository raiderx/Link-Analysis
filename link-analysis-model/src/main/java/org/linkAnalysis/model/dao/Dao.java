package org.linkAnalysis.model.dao;

import org.linkAnalysis.model.entity.DomainEntity;

/**
 * Base Data Access Object interface
 * Provides CRUD operations with {@link DomainEntity} objects
 *
 * @author Pavel Karpukhin
 */
public interface Dao<T extends DomainEntity> {

    /**
     * Creates the persistent object
     *
     * @param entity
     */
    void create(T entity);

    /**
     * Saves or updates the persistent object
     *
     * @param entity  object to save
     */
    void update(T entity);

    /**
     * Removes the object
     *
     * @param entity
     * @return
     */
    boolean delete(T entity);

    /**
     * Gets the object by its id
     *
     * @param id
     * @return
     */
    T get(int id);
}
