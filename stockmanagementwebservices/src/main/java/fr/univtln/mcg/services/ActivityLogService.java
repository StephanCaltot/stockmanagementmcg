package fr.univtln.mcg.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.univtln.mcg.ActivityLog;
import fr.univtln.mcg.business.ActivityLogManagerBean;

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
@Path("activitylogs")
public class ActivityLogService extends GenericService<ActivityLog> {
    @Inject
    ActivityLogManagerBean activityLogManagerBean;

    @GET
    @Path("nongen/{id}")
    public Response findNonGen(@PathParam("id") int id){
        ActivityLog activityLog = activityLogManagerBean.findActivityLogById(id);
        return Response.ok().entity(activityLog).build();
    }

    @GET
    @Path("nongen")
    public Response all() throws JsonProcessingException {
        List<ActivityLog> activitiesLog = activityLogManagerBean.findAllActivitiesLog();
        return Response.ok().entity(activitiesLog).build();
    }
}
