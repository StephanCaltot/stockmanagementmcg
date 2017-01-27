package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.TechnologicManagerBean;
import fr.univtln.mcg.material.technologic.Technologic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/*
 * REST Service at url /technologics
 * Provides ways to create/update an entity (see superclass)
 * and also get a technologic by his id or get all the technologics
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("technologics")
public class TechnologicService extends GenericService<Technologic> {

    @Inject
    TechnologicManagerBean technologicManagerBean;

    /**
     * @return Response with a json including all the
     * technologics in the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Technologic> technologics = technologicManagerBean.findAllTechnologics();
        String json = mapper.writerWithType(new TypeReference<List<Technologic>>() {}).writeValueAsString(technologics);
        return Response.ok().entity(json).build();
    }

    /**
     * @param id
     * @return Reponse with the Technologic loaded from the
     * database with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGenericById(@PathParam("id") int id){
        Technologic technologics = technologicManagerBean.findTechnologicById(id);
        return Response.ok().entity(technologics).build();
    }

}
