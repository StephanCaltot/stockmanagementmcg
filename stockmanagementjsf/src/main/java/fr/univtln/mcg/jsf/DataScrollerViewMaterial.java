package fr.univtln.mcg.jsf;

import fr.univtln.mcg.CRoom;
import fr.univtln.mcg.material.CMaterial;

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

    private List<CMaterial> materials;

    @ManagedProperty("#{materialService}")
    private CMaterialService service;

    @PostConstruct
    public void init() {
        materials = service.createMaterials();
    }

    public List<CMaterial> getMaterials() {
        return materials;
    }

    public void setService(CMaterialService service) {
        this.service = service;
    }
}
