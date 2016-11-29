package fr.univtln.mcg;


import fr.univtln.mcg.dao.CrudService;

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
    CrudService<T> crudService;

    private Class getType(){
        Class<T> type = null;
        ParameterizedType thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type dollarsT = thisClass.getActualTypeArguments()[0];
        if (dollarsT instanceof Class) {
            type = (Class<T>)dollarsT;
        }
        else if (dollarsT instanceof ParameterizedType) {
            type = (Class<T>)((ParameterizedType)dollarsT).getRawType();
        }
        return type;
    }

    @GET
    @Path("{id}")
    public T find(@PathParam("id") int id) {
        return crudService.find(getType(),id);
    }


    @GET
    public List<T> findAll() {
        return crudService.findWithNamedQuery("CRoom.findAll");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public T create(T t) {
        return crudService.create(t);
    }
}
