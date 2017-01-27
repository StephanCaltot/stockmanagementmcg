package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.ChalkManagerBean;
import fr.univtln.mcg.material.pedagogic.Chalk;

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
 * REST Service at url /chalks
 * Provides ways to create/update an entity (see superclass)
 * and also get a chalk by his id or get all the chalks
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("chalks")
public class ChalkService extends GenericService<Chalk> {

    @Inject
    ChalkManagerBean chalkManagerBean;

    /**
     * @param id
     * @return Response with the Chalk loaded from
     * the database with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Chalk chalk = chalkManagerBean.findChalkById(id);
        return Response.ok().entity(chalk).build();
    }

    /**
     * @return Reponse with all the Chalks in
     * the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<Chalk> chalks = chalkManagerBean.findAllChalks();
        return Response.ok().entity(chalks).build();
    }
}
