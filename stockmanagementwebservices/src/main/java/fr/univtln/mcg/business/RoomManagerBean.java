package fr.univtln.mcg.business;

import fr.univtln.mcg.Room;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

/**
 * Business class for the Rooms.
 * Provides way to do the basic CRUD operations
 * on the Room class.
 */

@Stateless
public class RoomManagerBean extends GenericManagerBean<Room> {

    public RoomManagerBean(){
        super();
    }

    public Room findRoomById(int id){
        return crudService.find(Room.class, id);
    }

    public List<Room> findAllRooms(){
        return (List<Room>) crudService.findWithNamedQuery(Room.GET_ALL);
    }

}
