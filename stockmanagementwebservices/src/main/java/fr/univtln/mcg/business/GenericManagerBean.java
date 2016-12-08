package fr.univtln.mcg.business;

import fr.univtln.mcg.dao.CrudService;
import net.jodah.typetools.TypeResolver;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by marti on 27/11/2016.
 */

@Stateless
@Local(IGenericManager.class)
public class GenericManagerBean<T> implements IGenericManager<T> {

    /*@Inject
    private GenericDao<T> mDao;*/
    @Inject
    CrudService<T> crudService;

    public Class getType(){/*
        Class<T> type = null;
        ParameterizedType thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type dollarsT = thisClass.getActualTypeArguments()[0];
        if (dollarsT instanceof Class) {
            type = (Class<T>)dollarsT;
        }
        else if (dollarsT instanceof ParameterizedType) {
            type = (Class<T>)((ParameterizedType)dollarsT).getRawType();
        }
        return type;*/
        Class<?>[] typeArgs = TypeResolver.resolveRawArguments(IGenericManager.class, GenericManagerBean.class);
        return typeArgs[0];
    }

    @Override
    public T create(T pT) {
        /*if (mDao.openTransaction()){
            pT = mDao.create(pT);
            mDao.commitTransaction();
        }
        return pT;*/
        return crudService.create(pT);
    }

    @Override
    public T update(T pT) {
        /*if (mDao.openTransaction()){
            pT = mDao.update(pT);
            mDao.commitTransaction();
        }
        return pT;  */
        return crudService.update(pT);
    }

    @Override
    public T find(int pId)
    {
        return crudService.find(getType(), pId);
    }

    @Override
    public void delete(int pId) {
        crudService.delete(getType(), pId);
    }

    @Override
    public List<T> findAll() {
        return crudService.findWithNamedQuery(getType().getSimpleName() + ".findAll");
    }


}
