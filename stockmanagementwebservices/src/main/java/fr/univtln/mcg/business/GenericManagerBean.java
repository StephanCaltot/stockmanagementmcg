package fr.univtln.mcg.business;

import fr.univtln.mcg.dao.CrudService;
import net.jodah.typetools.TypeResolver;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by marti on 27/11/2016.
 */

@Stateless
@Local(IGenericManager.class)
public class GenericManagerBean<T> implements IGenericManager<T> {

    @Inject
    CrudService<T> crudService;

    public Class getType(){
        Class<?>[] typeArgs = TypeResolver.resolveRawArguments(IGenericManager.class, GenericManagerBean.class);
        return typeArgs[0];
    }

    @Override
    public T create(T pT) {
        return crudService.create(pT);
    }

    @Override
    public T update(T pT) {
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
