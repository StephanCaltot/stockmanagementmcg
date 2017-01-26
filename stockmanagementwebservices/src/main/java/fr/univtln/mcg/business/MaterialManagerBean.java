package fr.univtln.mcg.business;

import fr.univtln.mcg.material.Material;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

@Stateless
public class MaterialManagerBean extends GenericManagerBean<Material> {

    public MaterialManagerBean(){
        super();
    }

    public Material findMaterialById(int pId){
        return crudService.find(Material.class, pId);
    }

    public List<Material> findAllMaterials(){
        return (List<Material>) crudService.findWithNamedQuery(Material.GET_ALL_MATERIALS);
    }
}
