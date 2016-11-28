package fr.univtln.mcg;

import java.util.List;
import java.util.Map;

/**
 * Created by marti on 28/11/2016.
 */
public interface IGenericDao<T> {

    T create(T pT);
    T update(T pT);
    T find(Class pType, int pId);
    void delete(Class pType, int pId);
    List<T> findAll();
    List<T> findWithNamedQuery(String pQuery);
    List<T> findWithNamedQuery(String pQuery, Map<String, Object> pParameters);
    List<T> findWithNamedQuery(String pQuery, Map<String, Object> pParameters, int pResultLimit);
    Boolean openTransaction();
    void commitTransaction();
    void clearCache();
}
