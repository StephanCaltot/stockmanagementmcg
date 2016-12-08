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


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("technologic")
public class TechnologicService extends GenericService<Technologic> {

    @Inject
    TechnologicManagerBean technologicManagerBean;

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Technologic> technologics = technologicManagerBean.findAllTechnologics();
        String json = mapper.writerWithType(new TypeReference<List<Technologic>>() {}).writeValueAsString(technologics);
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("nongen/{id}")
    public Response findNonGenericById(@PathParam("id") int id){
        Technologic technologics = technologicManagerBean.findTechnologicById(id);
        return Response.ok().entity(technologics).build();
    }

}
