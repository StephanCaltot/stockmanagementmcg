package fr.univtln.mcg;


import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Path("room")
public class CRoomServices extends AGenericServices<CRoom> {

}
