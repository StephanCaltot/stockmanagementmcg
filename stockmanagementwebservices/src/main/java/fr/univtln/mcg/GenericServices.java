package fr.univtln.mcg;


import fr.univtln.mcg.dao.CrudService;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
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
public abstract class GenericServices<T> {
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
    public Response find(@PathParam("id") int id) {
        T t = crudService.find(getType(),id);
        return Response.ok(t).entity(t).build();
    }


    @GET
    public Response findAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<T> t = crudService.findWithNamedQuery(getType().getSimpleName() + ".findAll");

        T tson = t.get(0);

        String jsonson = mapper.writeValueAsString(tson);
        System.out.println("json fils :\n" + jsonson);

        String json = mapper.writerWithType(new TypeReference<List<T>>() {
        }).writeValueAsString(t);
        String className = getType().getName();
        String interold = "\"@class\":\"fr.univtln.mcg.material.technologic.Computer\",";
        String inter = "\"@class\":\"" + className + "\",";

        System.out.println("json: \n" + json);
        String str = new StringBuilder(json).insert(2, inter).toString();
        System.out.println("strgitano :\n" + str);

        int nboccur = t.size() - 1;
        str = str.replace("true},{", "true},{"+inter);
        str = str.replace("false},{", "false},{"+inter);

        System.out.println("strgitano2 :\n" + str);



        return Response.ok().entity(str).build();
        //return t;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T t) {
        t = crudService.create(t);
        return Response.ok(t).entity(t).build();
    }



    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T t) {
        t = crudService.update(t);
        return Response.ok(t).entity(t).build();
    }


    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        crudService.delete(getType(),id);
        return Response.ok().build();
    }

}
