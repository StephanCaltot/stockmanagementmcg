package fr.univtln.mcg;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by marti on 28/11/2016.
 */
public class CGenericDao<T> implements IGenericDao<T>{

    EntityManagerFactory mEntityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager mEntityManager = mEntityManagerFactory.createEntityManager();

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
        Class<T> _type = null;
        ParameterizedType $thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type $T = $thisClass.getActualTypeArguments()[0];
        if ($T instanceof Class) {
            _type = (Class<T>)$T;
        }
        else if ($T instanceof ParameterizedType) {
            _type = (Class<T>)((ParameterizedType)$T).getRawType();
        }
        return findWithNamedQuery(_type.getSimpleName() + ".getAll");
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
