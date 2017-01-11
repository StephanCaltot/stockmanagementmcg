package fr.univtln.mcg.services;


import fr.univtln.mcg.Person;
import fr.univtln.mcg.business.PersonManagerBean;

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
@Path("people")
public class PersonService extends GenericService<Person> {

    @Inject
    PersonManagerBean personManager;

    @GET
    @Path("nongen/{id}")
    public Response findNonGeneric(@PathParam("id") int id) {
        Person person = personManager.findPersonById(id);
        return Response.ok().entity(person).build();
    }

    @GET
    @Path("nongen")
    public Response findAllNonGeneric(){
        List<Person> people = personManager.findAllPeople();
        return Response.ok().entity(people).build();
    }
}
