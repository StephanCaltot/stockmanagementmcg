package fr.univtln.mcg.jsf;

import fr.univtln.mcg.CRoom;

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

    private List<CRoom> rooms;

    @ManagedProperty("#{roomService}")
    private CRoomService service;

    @PostConstruct
    public void init() {
        rooms = service.createRooms();
    }

    public List<CRoom> getRooms() {
        return rooms;
    }

    public void setService(CRoomService service) {
        this.service = service;
    }
}
