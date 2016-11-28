package fr.univtln.mcg.utils;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bruno on 31/10/15.
 */
@Stateless
@Local(CrudService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CrudServiceBean<T> implements CrudService<T> {

    @PersistenceContext
    EntityManager mEntityManager;

    @Override
    public  T create(T t) {
        this.mEntityManager.persist(t);
        this.mEntityManager.flush();
        this.mEntityManager.refresh(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public  T find(Class pType,Object pId) {
        return (T) this.mEntityManager.find(pType, pId);
    }

    @Override
    public void delete(Class pType,Object pId) {
        Object ref = this.mEntityManager.getReference(pType, pId);
        this.mEntityManager.remove(ref);
    }

    @Override
    public  T update(T t) {
        return this.mEntityManager.merge(t);
    }

    @Override
    public List findWithNamedQuery(String pNamedQueryName){
        return this.mEntityManager.createNamedQuery(pNamedQueryName).getResultList();
    }

    @Override
    public List findWithNamedQuery(String pNamedQueryName, Map<String, Object> pParameters){
        return findWithNamedQuery(pNamedQueryName, pParameters, 0);
    }

    @Override
    public List findWithNamedQuery(String pQueryName, int pResultLimit) {
        return this.mEntityManager.createNamedQuery(pQueryName).setMaxResults(pResultLimit).getResultList();
    }

    public List findByNativeQuery(String pSql, Class pType) {
        return this.mEntityManager.createNativeQuery(pSql, pType).getResultList();
    }

    @Override
    public List findWithNamedQuery(String pNamedQueryName, Map<String, Object> pParameters,int pResultLimit){
        Set<Map.Entry<String, Object>> lRawParameters = pParameters.entrySet();
        Query lQuery = this.mEntityManager.createNamedQuery(pNamedQueryName);
        if(pResultLimit > 0)
            lQuery.setMaxResults(pResultLimit);
        for (Map.Entry<String, Object> entry : lRawParameters) {
            lQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return lQuery.getResultList();
    }
}