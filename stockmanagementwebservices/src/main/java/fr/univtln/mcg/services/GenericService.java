package fr.univtln.mcg.services;


import fr.univtln.mcg.business.IGenericManager;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by screetts on 16/11/16.
 */
@Stateless
@Local
public abstract class GenericService<T> {

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
        T t = genericManager.find(id);
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
        T updatedT = genericManager.create(t);
        return Response.ok(updatedT).entity(updatedT).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T t) {
        T updatedT = genericManager.update(t);
        return Response.ok(updatedT).entity(updatedT).build();
    }


    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        genericManager.delete(id);
        return Response.ok().build();
    }

}
