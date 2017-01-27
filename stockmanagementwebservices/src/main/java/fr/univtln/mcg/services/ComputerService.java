package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

/*
 * REST Service at url /computers
 * Provides ways to create/update an entity (see superclass)
 * and also get a computer by his id or get all the computers
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("computers")
public class ComputerService extends GenericService<Computer> {
    @Inject
    ComputerManagerBean computerManagerBean;


    /**
     * @param id
     * @return Response with the Computer loaded from the database
     * with the id in parameter
     */
    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Computer computer = computerManagerBean.findComputerById(id);
        return Response.ok().entity(computer).build();
    }

    /**
     * @return Response with the json including all the Computers
     * in the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<Computer> computers = computerManagerBean.findAllComputers();
        return Response.ok().entity(computers).build();
    }
}
