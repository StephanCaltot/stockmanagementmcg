package fr.univtln.mcg;

import fr.univtln.mcg.utils.CrudService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by screetts on 16/11/16.
 */
@Stateless
public abstract class AGenericServices<T> {
    
    @Inject
    CrudService<T> mCrudService;

    private Class genType(){
        Class<T> lType = null;
        ParameterizedType $thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type $T = $thisClass.getActualTypeArguments()[0];
        if ($T instanceof Class){
            lType = (Class<T>) $T;
        }

        else if ($T instanceof ParameterizedType) {
            lType = (Class <T>) ((ParameterizedType) $T).getRawType();
        }
        return lType;
    }

    @GET
    @Path("{id}")
    public T find(@PathParam("id") int id) {
        return mCrudService.find(genType(), id);
    }


    @GET
    public List<T> findAll() {
        return mCrudService.findWithNamedQuery(genType().getSimpleName() + ".findAll");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public T create(T t) {
        return mCrudService.create(t);
    }
}
