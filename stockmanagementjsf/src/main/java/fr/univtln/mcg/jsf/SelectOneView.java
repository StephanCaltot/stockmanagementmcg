package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 30/11/16.
 */

import fr.univtln.mcg.CRoom;
import fr.univtln.mcg.material.CMaterial;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;


@ManagedBean
public class SelectOneView {
    private List<CRoom> rooms;
    private List<CMaterial> materials;

    @ManagedProperty("#{roomService}")
    private CRoomService serviceRoom;

    @ManagedProperty("#{materialService}")
    private CMaterialService serviceMaterials;

    @PostConstruct
    public void init() {
        rooms = serviceRoom.createRooms();
        materials = serviceMaterials.createMaterials();

    }

    public List<CRoom> getRooms() {
        return rooms;
    }

    public void setServiceRoom(CRoomService service) {
        this.serviceRoom = service;
    }

    public List<CMaterial> getMaterials() {
        return materials;
    }

    public void setServiceMaterials(CMaterialService service) {
        this.serviceMaterials = service;
    }
}