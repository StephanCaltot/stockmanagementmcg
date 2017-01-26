package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univtln.mcg.business.ArmChairManagerBean;
import fr.univtln.mcg.material.pedagogic.ArmChair;

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
@Path("armchairs")
public class ArmChairService extends GenericService<ArmChair> {

    @Inject
    ArmChairManagerBean armChairManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        ArmChair armChair = armChairManagerBean.findArmChairById(id);
        return Response.ok().entity(armChair).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        List<ArmChair> armChairs = armChairManagerBean.findAllArmChairs();
        return Response.ok().entity(armChairs).build();
    }


}
