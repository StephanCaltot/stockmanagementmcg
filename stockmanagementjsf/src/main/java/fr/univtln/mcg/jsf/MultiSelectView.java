package fr.univtln.mcg.jsf;

import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.Material;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 * Created by jlng on 05/12/16.
 */
@ManagedBean
@ViewScoped
public class MultiSelectView {

    @ManagedProperty("#{materialService}")
    private MaterialService serviceMaterial;

    @ManagedProperty("#{roomService}")
    private RoomService serviceRoom;

    private static List<SelectItem> categories = new ArrayList<>();
    private static List<String> categoriesName = new ArrayList<>();

    private List<Material> materials;
    private Material selectionMaterial;

    private List<Room> rooms;
    private Room selectionRoom;

    @PostConstruct
    public void init() {
        materials = serviceMaterial.create();
        rooms = serviceRoom.create();
    }

    public MaterialService getServiceMaterial() {
        return serviceMaterial;
    }

    public void setServiceMaterial(MaterialService serviceMaterial) {
        this.serviceMaterial = serviceMaterial;
    }

    public RoomService getServiceRoom() {
        return serviceRoom;
    }

    public void setServiceRoom(RoomService serviceRoom) {
        this.serviceRoom = serviceRoom;
    }

    public static List<SelectItem> getCategories() {
        return categories;
    }

    public static void setCategories(List<SelectItem> categories) {
        MultiSelectView.categories = categories;
    }

    public static List<String> getCategoriesName() {
        return categoriesName;
    }

    public static void setCategoriesName(List<String> categoriesName) {
        MultiSelectView.categoriesName = categoriesName;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public Material getSelectionMaterial() {
        return selectionMaterial;
    }

    public void setSelectionMaterial(Material selectionMaterial) {
        this.selectionMaterial = selectionMaterial;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Room getSelectionRoom() {
        return selectionRoom;
    }

    public void setSelectionRoom(Room selectionRoom) {
        this.selectionRoom = selectionRoom;
    }
}