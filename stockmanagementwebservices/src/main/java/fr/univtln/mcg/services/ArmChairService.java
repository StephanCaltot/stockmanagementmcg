package fr.univtln.mcg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

/*
 * REST Service at url /armchairs
 * Provides ways to create/update an entity (see superclass)
 * and also get a armchair by his id or get all the armchairs
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("armchairs")
public class ArmChairService extends GenericService<ArmChair> {

    @Inject
    ArmChairManagerBean armChairManagerBean;

    /**
     *
     * @param id
     * @return Response with the ArmChair from the database
     * with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        ArmChair armChair = armChairManagerBean.findArmChairById(id);
        return Response.ok().entity(armChair).build();
    }

    /**
     * @return Response with all the ArmChairs in the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<ArmChair> armChairs = armChairManagerBean.findAllArmChairs();
        return Response.ok().entity(armChairs).build();
    }


}
