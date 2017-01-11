package fr.univtln.mcg;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by marti on 27/11/2016.
 */

@Stateless
public class CGenericManagerBean<T> implements IGenericManager<T> {

    @Inject
    private GenericDao<T> mDao;

    @Override
    public T create(T pT) {
        if (mDao.openTransaction()){
            pT = mDao.create(pT);
            mDao.commitTransaction();
        }
        return pT;
    }

    @Override
    public T update(T pT) {
        if (mDao.openTransaction()){
            pT = mDao.update(pT);
            mDao.commitTransaction();
        }
        return pT;    }

    @Override
    public T find(int pId)
    {
        Class<T> _type = null;
        ParameterizedType $thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type $T = $thisClass.getActualTypeArguments()[0];
        if ($T instanceof Class) {
            _type = (Class<T>)$T;
        }
        else if ($T instanceof ParameterizedType) {
            _type = (Class<T>)((ParameterizedType)$T).getRawType();
        }
        return mDao.find(_type, pId);
    }

    @Override
    public void delete(int pId) {
        Class<T> _type = null;
        ParameterizedType $thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type $T = $thisClass.getActualTypeArguments()[0];
        if ($T instanceof Class) {
            _type = (Class<T>)$T;
        }
        else if ($T instanceof ParameterizedType) {
            _type = (Class<T>)((ParameterizedType)$T).getRawType();
        }
        mDao.delete(_type, pId);
    }

    @Override
    public List<T> findAll() {
        return mDao.findAll();
    }


}
