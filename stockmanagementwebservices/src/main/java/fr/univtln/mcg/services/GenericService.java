package fr.univtln.mcg.services;


import fr.univtln.mcg.business.IGenericManager;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by screetts on 16/11/16.
 */

/*
 * Generic service every other REST services
 * will inheritate from.
 * Only contains C and U from the CRUD because of an issue
 * about the genericity  on the R and D with jackson when deserializing
 * into a class inheriting from an abstract class (can't find the class)
 */

@Stateless
@Local
public abstract class GenericService<T> {
    @Inject
    IGenericManager<T> genericManager;


    /**
     * @param t entity to create
     * @return Response with the created entity
     * When accessing the url /<classname> (plural) with POST
     * we persist the passed entity.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T t) {
        t = genericManager.create(t);
        System.out.println("object cree : " + t.getClass().getSimpleName());
        return Response.ok(t).entity(t).build();
    }

    /**
     * @param t entity to update
     * @return Response with the updated entity
     * When accessing the url /<classname> (plural) with PUT
     * we update the passed entity
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T t) {
        t = genericManager.update(t);
        return Response.ok(t).entity(t).build();
    }

}
