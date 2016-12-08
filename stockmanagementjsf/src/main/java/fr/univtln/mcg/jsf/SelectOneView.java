package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 30/11/16.
 */

import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.Material;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;


@ManagedBean
public class SelectOneView {
    private List<Room> rooms;
    private List<Material> materials;

    @ManagedProperty("#{roomService}")
    private RoomService serviceRoom;

    @ManagedProperty("#{materialService}")
    private MaterialService serviceMaterials;

    @PostConstruct
    public void init() {
        rooms = serviceRoom.create();
        materials = serviceMaterials.create();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setServiceRoom(RoomService service) {
        this.serviceRoom = service;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setServiceMaterials(MaterialService service) {
        this.serviceMaterials = service;
    }
}