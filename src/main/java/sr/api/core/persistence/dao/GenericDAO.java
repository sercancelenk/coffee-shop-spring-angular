package sr.api.core.persistence.dao;

/**
 * Created by byzas on 15/01/16.
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    T getById(ID id, boolean lock);

    T getById(ID id);

    T loadById(ID id);

    List<T> loadAll();

    List<T> loadAll(int start, int end);

    ID save(T entity);

    void update(T entity);

    void saveOrUpdate(T entitsaveOrUpdateAlly);

    void saveOrUpdateAll(Collection<T> entityCollection);

    void delete(T entity);

    void deleteById(ID id);

    void deleteAll(Collection<T> entityCollection);

    T merge(T entity);

    void initialize(Object object);

    void refresh(T entity);

    void evict(T entity);

}