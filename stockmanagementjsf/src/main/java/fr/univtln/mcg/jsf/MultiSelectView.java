package fr.univtln.mcg.jsf;

import fr.univtln.mcg.material.Material;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;


/**
 * Created by jlng on 05/12/16.
 */
@ManagedBean
public class MultiSelectView {

    private static List<SelectItem> categories = new ArrayList<SelectItem>();
    private static List<String> categoriesName = new ArrayList<String>();

    private List<Material> materials;
    private List<List<Material>> listMaterials;
    private String selection;

    @ManagedProperty("#{materialService}")
    private MaterialService service;

    @PostConstruct
    public void init() {
        List<List<SelectItem>> test = new ArrayList<>();
        SelectItem option;
        materials = service.createMaterials();
        //getList(materials);
        List cat = new ArrayList();
        int pos;
        List<SelectItemGroup> groups = new ArrayList<>();
        for (Material o : materials)
        {
            String className = o.getClass().getSimpleName();
            if(! categoriesName.contains(className))
            {
                groups.add(new SelectItemGroup(className));
                //categories.add(groups.get(groups.size()));
                categoriesName.add(className);
                cat.add(new ArrayList<>());
                test.add(new ArrayList<SelectItem>());
            }
            pos = categoriesName.indexOf(o.getClass().getSimpleName());
            ((List)cat.get(pos)).add(o);
            option = new SelectItem(o.toString());
            ((List)test.get(pos)).add(option);
        }
        for (int i = 0; i < groups.size(); i++)
        {
            groups.get(i).setSelectItems(test.get(i).toArray(new SelectItem[test.get(i).size()]));
        }
        categories.addAll(groups);
    }

    public static void getList(List l)
    {
        //List categories = new ArrayList();
        List cat = new ArrayList();
        int pos;
        SelectItemGroup group;;
        for (Object o : l)
        {
            String className = o.getClass().getSimpleName();
            if(! categories.contains(className))
            {
                group = new SelectItemGroup(o.getClass().getSimpleName());
                categories.add(group);
                cat.add(new ArrayList<>());
            }
            pos = categories.indexOf(o.getClass().getSimpleName());
            ((List)cat.get(pos)).add(o);
        }
        //SelectItem option = new SelectItem("Option 1.2.3");

//        groups.get(0).setSelectItems(new SelectItem[]{option});

    }

    public List<SelectItem> getCategories() {
        return categories;
    }

    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
    }

    public static void setCategories(List<SelectItem> categories) {
        MultiSelectView.categories = categories;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<List<Material>> getListMaterials() {
        return listMaterials;
    }

    public void setListMaterials(List<List<Material>> listMaterials) {
        this.listMaterials = listMaterials;
    }

    public MaterialService getService() {
        return service;
    }

    public void setService(MaterialService service) {
        this.service = service;
    }
}