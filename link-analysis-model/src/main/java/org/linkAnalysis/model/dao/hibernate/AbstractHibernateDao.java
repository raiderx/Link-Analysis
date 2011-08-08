package org.linkAnalysis.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.linkAnalysis.model.dao.Dao;
import org.linkAnalysis.model.entity.DomainEntity;

import java.lang.reflect.ParameterizedType;

/**
 * @author Pavel Karpukhin
 */
public abstract class AbstractHibernateDao<T extends DomainEntity>
        implements Dao<T> {

    private SessionFactory sessionFactory;
    private final Class<T> type = getType();

    private Class<T> getType() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void create(T entity) {
        if (null == entity)
            throw new IllegalArgumentException("Unable to create null object");
        if (entity.getId() != null)
            throw new IllegalStateException("Unable to create entity with id");
        entity.setActive(true);
        entity.setCreationDate(new DateTime());
        Session session = getSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void update(T entity) {
        if (null == entity)
            throw new IllegalArgumentException("Unable to update null object");
        if (entity.getId() == null)
            throw new IllegalStateException("Unable to update entity without id");
        Session session = getSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public boolean delete(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("Unable to remove null object");
        if (entity.getActive() == null || entity.getActive() == false)
            throw new IllegalStateException("Unable to remove earlier removed entity");
        entity.setActive(false);
        Session session = getSession();
        session.saveOrUpdate(entity);
        return true;
    }

    @Override
    public T get(int id) {
        return (T) getSession().get(type, id);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
