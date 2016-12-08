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

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("chalks")
public class ChalkService extends GenericService<Chalk> {

    @Inject
    ChalkManagerBean chalkManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Chalk chalk = chalkManagerBean.findChalkById(id);
        return Response.ok().entity(chalk).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Chalk> chalks = chalkManagerBean.findAllChalks();
        //String json = mapper.writerWithType(new TypeReference<List<Computer>>() {}).writeValueAsString(computers);
        return Response.ok().entity(chalks).build();
    }
}
