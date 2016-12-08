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
import javax.faces.model.SelectItemGroup;


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

    private static List<SelectItem> categories = new ArrayList<SelectItem>();
    private static List<String> categoriesName = new ArrayList<String>();

    private List<Material> materials;
    private Material selectionMaterial;

    private List<Room> rooms;
    private Room selectionRoom;

    @PostConstruct
    public void init() {
        materials = serviceMaterial.create();
        rooms = serviceRoom.create();
        /*
        List<List<SelectItem>> test = new ArrayList<>();
        categories = new ArrayList<SelectItem>();
        categoriesName = new ArrayList<String>();
        SelectItem option;
        //getList(materials);
        List<List> cat = new ArrayList();
        int pos;
        List<SelectItemGroup> groups = new ArrayList<>();
        for (Material o : materials)
        {
            String className = o.getClass().getSimpleName();
            if(! categoriesName.contains(className))
            {
                groups.add(new SelectItemGroup(className));
                categoriesName.add(className);
                cat.add(new ArrayList<>());
                test.add(new ArrayList<SelectItem>());
            }
            pos = categoriesName.indexOf(className);
            ((List)cat.get(pos)).add(o);
            option = new SelectItem(o.toString());
            ((List)test.get(pos)).add(option);
        }
        for (int i = 0; i < groups.size(); i++)
        {
            groups.get(i).setSelectItems(test.get(i).toArray(new SelectItem[test.get(i).size()]));
        }
        categories.addAll(groups);
        */
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