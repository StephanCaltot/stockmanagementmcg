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

/*
 * REST Service at url /people
 * Provides ways to create/update an entity (see superclass)
 * and also get a person by his id or get all the people
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("people")
public class PersonService extends GenericService<Person> {

    @Inject
    PersonManagerBean personManager;

    /**
     * @param id
     * @return Response with the person loaded from
     * the database with this id
     */

    @GET
    @Path("/{id}")
    public Response findNonGeneric(@PathParam("id") int id) {
        Person person = personManager.findPersonById(id);
        return Response.ok().entity(person).build();
    }

    /**
     * @return Response with all the people in the
     * people in the database
     */

    @GET
    public Response findAllNonGeneric(){
        List<Person> people = personManager.findAllPeople();
        return Response.ok().entity(people).build();
    }
}
