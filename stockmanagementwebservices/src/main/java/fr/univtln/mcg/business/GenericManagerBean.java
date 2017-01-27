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

/*
 * Generic bean providing business methods for all the classes that
 * will inheritate from it.
 */

@Stateless
@Local(IGenericManager.class)
public class GenericManagerBean<T> implements IGenericManager<T> {

    @Inject
    CrudService<T> crudService;

    /**
     * @param pT entity to persist
     * @return the persisted entity
     */
    @Override
    public T create(T pT) {
        return crudService.create(pT);
    }

    /**
     * @param pT entity to update
     * @return the updated entity
     */
    @Override
    public T update(T pT) {
        return crudService.update(pT);
    }

}
