package fr.univtln.mcg.business;

import fr.univtln.mcg.material.Material;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

/**
 * Business class for the Materials.
 * Provides way to do the basic CRUD operations
 * on the Material class.
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
        return (List<Material>) crudService.findWithNamedQuery(Material.GET_ALL);
    }
}
