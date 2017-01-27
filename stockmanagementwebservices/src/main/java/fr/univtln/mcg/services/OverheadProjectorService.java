package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.OverheadProjectorManagerBean;
import fr.univtln.mcg.material.technologic.OverheadProjector;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

/*
 * REST Service at url /overheadprojectors
 * Provides ways to create/update an entity (see superclass)
 * and also get a overheadprojector by his id or get all the overheadprojectors
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("overheadprojectors")
public class OverheadProjectorService extends GenericService<OverheadProjector> {
    @Inject
    OverheadProjectorManagerBean overheadProjectorManagerBean;

    /**
     * @param id
     * @return Response with the overheadprojector loaded from
     * the database with this id
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        OverheadProjector overheadProjector = overheadProjectorManagerBean.findOverheadProjectorById(id);
        return Response.ok().entity(overheadProjector).build();
    }

    /**
     * @return
     * @throws JsonProcessingException
     * Response with the list of all the overhead projectors
     * in the database
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<OverheadProjector> overheadProjectors = overheadProjectorManagerBean.findAllOverheadProjectors();
        return Response.ok().entity(overheadProjectors).build();
    }
}
