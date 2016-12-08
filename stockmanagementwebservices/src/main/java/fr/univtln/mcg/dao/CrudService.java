package fr.univtln.mcg.dao;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 31/10/15.
 */

@Local
public interface CrudService<T> {
    public T create(T t);

    public T find(Class type, Object id);

    public T update(T t);

    public void delete(Class type, Object id);

    public List findWithNamedQuery(String queryName);

    public List findWithNamedQuery(String queryName, int resultLimit);

    public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit);
}