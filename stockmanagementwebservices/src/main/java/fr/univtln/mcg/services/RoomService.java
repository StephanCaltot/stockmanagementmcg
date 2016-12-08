package fr.univtln.mcg.services;


import fr.univtln.mcg.Room;
import fr.univtln.mcg.business.RoomManagerBean;

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
@Path("rooms")
public class RoomService extends GenericService<Room> {

    @Inject
    RoomManagerBean roomManager;

    @GET
    @Path("nongen/{id}")
    public Response findNonGeneric(@PathParam("id") int id) {
        Room room = /*crudService.find(getType(),id);*/roomManager.findRoomById(id);
        return Response.ok(room).entity(room).build();
    }

    @GET
    @Path("nongen")
    public Response findAllNonGeneric(){
        List<Room> rooms = roomManager.findAllRooms();
        return Response.ok().entity(rooms).build();
    }
}
