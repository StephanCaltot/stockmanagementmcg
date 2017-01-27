package fr.univtln.mcg.business;

import fr.univtln.mcg.material.technologic.OverheadProjector;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

/**
 * Business class for the OverheadProjectors.
 * Provides way to do the basic CRUD operations
 * on the OverheadProjector class.
 */

@Stateless
public class OverheadProjectorManagerBean extends GenericManagerBean<OverheadProjector> {

    public OverheadProjectorManagerBean(){
        super();
    }

    public OverheadProjector findOverheadProjectorById(int pId){
        return crudService.find(OverheadProjector.class, pId);
    }

    public List<OverheadProjector> findAllOverheadProjectors(){
        return (List<OverheadProjector>) crudService.findWithNamedQuery(OverheadProjector.GET_ALL);
    }
}
