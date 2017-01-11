package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univtln.mcg.Activity;
import fr.univtln.mcg.business.ActivityManagerBean;

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
@Path("activities")
public class ActivityService extends GenericService<Activity> {
    @Inject
    ActivityManagerBean activityManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        Activity activity = activityManagerBean.findActivityById(id);
        return Response.ok().entity(activity).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        List<Activity> activities = activityManagerBean.findAllActivities();
        return Response.ok().entity(activities).build();
    }
}
