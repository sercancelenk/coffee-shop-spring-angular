package sr.api.core.persistence.dao.impl;

/**
 * Created by byzas on 15/01/16.
 */
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public abstract class AbstractHibernateDAO<T, ID extends Serializable> {

    protected Class<T> persistentClass;
    protected HibernateTemplate hibernateTemplate;

    @SuppressWarnings("unchecked")
    public AbstractHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    @Qualifier("sessionFactory")
    public void setHibernateTemplate(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public T getById(ID id) {
        return hibernateTemplate.get(persistentClass, id);
    }

    public T getById(ID id, boolean lock) {
        if (lock) {
            return hibernateTemplate.get(persistentClass, id, LockMode.UPGRADE);
        } else
            return getById(id);
    }

    public T loadById(ID id) {
        return hibernateTemplate.load(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public ID save(T entity) {
        return (ID) hibernateTemplate.save(entity);
    }

    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    public void saveOrUpdate(T entity) {
        hibernateTemplate.saveOrUpdate(entity);
    }

    public void saveOrUpdateAll(Collection<T> entityCollection) {
        for(T tt: entityCollection){
            hibernateTemplate.saveOrUpdate(tt);
        }

    }

    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

    public void deleteById(ID id) {
        hibernateTemplate.delete(loadById(id));
    }

    public void deleteAll(Collection<T> entityCollection) {
        hibernateTemplate.deleteAll(entityCollection);
    }

    @SuppressWarnings("unchecked")
    public List<T> loadAll() {
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(persistentClass));

    }

    @SuppressWarnings("unchecked")
    public List<T> loadAll(int start, int end) {
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(persistentClass), start, end);
    }

    public T merge(T entity) {
        return hibernateTemplate.merge(entity);
    }

    public void initialize(Object proxy) {
        hibernateTemplate.initialize(proxy);
    }

    public void refresh(T entity) {
        hibernateTemplate.refresh(entity);
    }

    public void evict(T entity) {
        hibernateTemplate.evict(entity);
    }
}