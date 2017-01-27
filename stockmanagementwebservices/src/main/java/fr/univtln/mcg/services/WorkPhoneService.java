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

/*
 * REST Service at url /workphones
 * Provides ways to create/update an entity (see superclass)
 * and also get a workphone by his id or get all the workphones
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("workphones")
public class WorkPhoneService extends GenericService<WorkPhone> {

    @Inject
    WorkPhoneManagerBean workPhoneManagerBean;

    /**
     * @param id
     * @return Response with the WorkPhone loaded from
     * the dabatase with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        WorkPhone workPhone = workPhoneManagerBean.findWorkPhoneById(id);
        return Response.ok().entity(workPhone).build();
    }

    /**
     * @return Response with all the WorkPhones in the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<WorkPhone> workPhones = workPhoneManagerBean.findAllWorkPhones();
        return Response.ok().entity(workPhones).build();
    }
}
