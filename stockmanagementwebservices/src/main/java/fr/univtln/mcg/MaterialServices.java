package fr.univtln.mcg;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.material.Material;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("materials")
public class MaterialServices extends GenericServices<Material> {

    @GET
    @Path("all")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Material> materials = crudService.findWithNamedQuery("Material.findAll");
        String json = mapper.writerWithType(new TypeReference<List<Material>>() {}).writeValueAsString(materials);
        return Response.ok().entity(json).build();
    }

}
