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

/*
 * REST Service at url /rooms
 * Provides ways to create/update an entity (see superclass)
 * and also get a room by his id or get all the rooms
 * from the database.
 */


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("rooms")
public class RoomService extends GenericService<Room> {

    @Inject
    RoomManagerBean roomManager;

    /**
     * @param id
     * @return Response with the room loaded from the database
     * with the id in parameter
     */

    @GET
    @Path("/{id}")
    public Response findNonGeneric(@PathParam("id") int id) {
        Room room = roomManager.findRoomById(id);
        return Response.ok(room).entity(room).build();
    }

    /**
     * @return Reponse with all the rooms
     * in the database
     */

    @GET
    public Response findAllNonGeneric(){
        List<Room> rooms = roomManager.findAllRooms();
        return Response.ok().entity(rooms).build();
    }
}
