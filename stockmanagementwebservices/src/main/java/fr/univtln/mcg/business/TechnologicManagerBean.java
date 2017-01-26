package fr.univtln.mcg.business;

import fr.univtln.mcg.material.technologic.Technologic;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 07/12/2016.
 */

@Stateless
public class TechnologicManagerBean extends GenericManagerBean<Technologic> {

    public TechnologicManagerBean(){
        super();
    }

    public Technologic findTechnologicById(int pId){
        return crudService.find(Technologic.class, pId);
    }

    public List<Technologic> findAllTechnologics(){
        return (List<Technologic>) crudService.findWithNamedQuery(Technologic.GET_ALL_TECHNOLOGIC);
    }
}
