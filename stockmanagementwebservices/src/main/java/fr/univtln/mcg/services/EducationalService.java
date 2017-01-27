package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.EducationalManagerBean;
import fr.univtln.mcg.material.pedagogic.Educational;

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
 * REST Service at url /educationals
 * Provides ways to create/update an entity (see superclass)
 * and also get a educational by his id or get all the educationals
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("educationals")
public class EducationalService extends GenericService<Educational> {

    @Inject
    EducationalManagerBean educationalManagerBean;

    /**
     * @param id
     * @return Response with the Educational loaded
     * from the database with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Educational educational = educationalManagerBean.findEducationalById(id);
        return Response.ok().entity(educational).build();
    }

    /**
     * @return Response with the json including all the Educationals
     * in the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Educational> educationals = educationalManagerBean.findAllEducationals();
        String json = mapper.writerWithType(new TypeReference<List<Educational>>() {}).writeValueAsString(educationals);
        return Response.ok().entity(json).build();
    }
}
