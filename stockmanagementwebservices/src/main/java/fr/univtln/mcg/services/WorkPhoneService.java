package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.business.WorkPhoneManagerBean;
import fr.univtln.mcg.material.technologic.WorkPhone;

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
@Path("workphones")
public class WorkPhoneService extends GenericService<WorkPhone> {

    @Inject
    WorkPhoneManagerBean workPhoneManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        WorkPhone workPhone = workPhoneManagerBean.findWorkPhoneById(id);
        return Response.ok().entity(workPhone).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<WorkPhone> workPhones = workPhoneManagerBean.findAllWorkPhones();
        //String json = mapper.writerWithType(new TypeReference<List<Computer>>() {}).writeValueAsString(computers);
        return Response.ok().entity(workPhones).build();
    }
}
