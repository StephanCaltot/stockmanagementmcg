package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univtln.mcg.Activity;
import fr.univtln.mcg.business.ActivityManagerBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/*
 * REST Service at url /activities
 * Provides ways to create/update an entity (see superclass)
 * and also get a activity by his id or get all the activities
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("activities")
public class ActivityService extends GenericService<Activity> {
    @Inject
    ActivityManagerBean activityManagerBean;

    /**
     * @param id
     * @return Response with the Activity loaded from
     * the database with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Activity activity = activityManagerBean.findActivityById(id);
        return Response.ok().entity(activity).build();
    }

    /**
     *
     * @return Response with the json including all the
     * Activities in the database
     * @throws JsonProcessingException
     */

    @GET
    public Response all() throws JsonProcessingException {
        List<Activity> activities = activityManagerBean.findAllActivities();
        return Response.ok().entity(activities).build();
    }
}
