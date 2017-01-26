package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univtln.mcg.business.ComputerManagerBean;
import fr.univtln.mcg.material.technologic.Computer;

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
@Path("computers")
public class ComputerService extends GenericService<Computer> {
    @Inject
    ComputerManagerBean computerManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Computer computer = computerManagerBean.findComputerById(id);
        return Response.ok().entity(computer).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        List<Computer> computers = computerManagerBean.findAllComputers();
        return Response.ok().entity(computers).build();
    }
}
