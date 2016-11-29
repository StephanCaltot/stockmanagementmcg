package fr.univtln.mcg;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by marti on 28/11/2016.
 */
@Stateless
@Local(CGenericDao.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CGenericDao<T> implements IGenericDao<T>{

    @PersistenceContext(unitName = "stockmanagement" )
    EntityManager mEntityManager;

    public T create(T pT) {
        mEntityManager.persist(pT);
        mEntityManager.flush();
        mEntityManager.refresh(pT);
        return pT;
    }

    public T update(T pT) {
        pT = mEntityManager.merge(pT);
        mEntityManager.flush();
        mEntityManager.refresh(pT);
        return pT;
    }

    public T find(Class pType, int pId) {
        return (T) mEntityManager.find(pType, pId);
    }

    public void delete(Class pType, int pId) {
        Object lRef = mEntityManager.getReference(pType, pId);
        mEntityManager.remove(lRef);
    }

    public List<T> findAll() {
        Class<T> type = null;
        ParameterizedType thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type dollarsT = thisClass.getActualTypeArguments()[0];
        if (dollarsT instanceof Class) {
            type = (Class<T>)dollarsT;
        }
        else if (dollarsT instanceof ParameterizedType) {
            type = (Class<T>)((ParameterizedType)dollarsT).getRawType();
        }
        return findWithNamedQuery(type.getSimpleName() + ".getAll");
    }

    public List<T> findWithNamedQuery(String pQuery) {
        return (List<T>) mEntityManager.createNamedQuery(pQuery).getResultList();
    }

    public List<T> findWithNamedQuery(String pQuery, Map<String, Object> pParameters) {
        return findWithNamedQuery(pQuery, pParameters, 0);
    }

    public List<T> findWithNamedQuery(String pQuery, Map<String, Object> pParameters, int pResultLimit) {
        Set<Map.Entry<String, Object>> lRawParameters = pParameters.entrySet();
        Query lQuery = mEntityManager.createNamedQuery(pQuery);
        if(pResultLimit > 0)
            lQuery.setMaxResults(pResultLimit);
        for (Map.Entry entry : lRawParameters) {
            lQuery.setParameter((String)entry.getKey(), entry.getValue());
        }
        return (List<T>) lQuery.getResultList();
    }

    public Boolean openTransaction() {
        if (!mEntityManager.getTransaction().isActive()){
            mEntityManager.getTransaction().begin();
            return true;
        }
        else{
            return false;
        }
    }

    public void commitTransaction() {
        mEntityManager.getTransaction().commit();
    }

    public void clearCache() {
        mEntityManager.getEntityManagerFactory().getCache().evictAll();
    }
}
