package fr.univtln.mcg.services;


import fr.univtln.mcg.business.GenericManagerBean;
import fr.univtln.mcg.business.IGenericManager;
import fr.univtln.mcg.dao.CrudService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by screetts on 16/11/16.
 */
@Stateless
@Local
public abstract class GenericService<T> {
    /*@Inject
    CrudService<T> crudService;*/
    @Inject
    IGenericManager<T> genericManager;

    public Class getType(){
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
    public Response find(@PathParam("id") int id) {
        T t = /*crudService.find(getType(),id);*/genericManager.find(id);
        return Response.ok(t).entity(t).build();
    }


    @GET
    public Response findAll() throws IOException {
        List<T> t = genericManager.findAll();
        return Response.ok().entity(t).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T t) {
        t = /*crudService.create(t);*/genericManager.create(t);
        System.out.println("object cree : " + t.getClass().getSimpleName());
        return Response.ok(t).entity(t).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T t) {
        t = /*crudService.update(t);*/ genericManager.update(t);
        return Response.ok(t).entity(t).build();
    }


    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        //crudService.delete(getType(),id);
        genericManager.delete(id);
        return Response.ok().build();
    }

}
