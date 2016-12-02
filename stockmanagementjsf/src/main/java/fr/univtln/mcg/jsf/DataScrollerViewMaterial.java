package fr.univtln.mcg.jsf;

import fr.univtln.mcg.material.Material;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jlng on 29/11/16.
 */

@ManagedBean
@ViewScoped
public class DataScrollerViewMaterial implements Serializable {

    private List<Material> materials;

    @ManagedProperty("#{materialService}")
    private MaterialService service;

    @PostConstruct
    public void init() {
        materials = service.createMaterials();
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setService(MaterialService service) {
        this.service = service;
    }
}
