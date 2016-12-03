package fr.univtln.mcg;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.pedagogic.Educational;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("educational")
public class EducationalServices extends GenericServices<Educational> {

    @GET
    @Path("all")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Educational> educationals = crudService.findWithNamedQuery("Educational.findAll");
        String json = mapper.writerWithType(new TypeReference<List<Material>>() {}).writeValueAsString(educationals);
        return Response.ok().entity(json).build();
    }
}
