package fr.univtln.mcg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.technologic.Technologic;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("technologics")
public class TechnologicServices extends GenericServices<Technologic> {

    @GET
    @Path("all")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Technologic> technologics = crudService.findWithNamedQuery("Technologic.findAll");
        String json = mapper.writerWithType(new TypeReference<List<Material>>() {}).writeValueAsString(technologics);
        return Response.ok().entity(json).build();
    }

}
