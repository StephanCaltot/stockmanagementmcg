package fr.univtln.mcg.jsf;


import fr.univtln.mcg.Room;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by jlng on 29/11/16.
 */

@ManagedBean
@ViewScoped
public class DataScrollerViewRoom implements Serializable {

    private List<Room> rooms;

    @ManagedProperty("#{roomService}")
    private RoomService service;

    @PostConstruct
    public void init() {
        rooms = service.create();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setService(RoomService service) {
        this.service = service;
    }
}
