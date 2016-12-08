package fr.univtln.mcg.business;

import fr.univtln.mcg.material.pedagogic.ArmChair;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

@Stateless
public class ArmChairManagerBean extends GenericManagerBean<ArmChair> {
    public ArmChairManagerBean(){
        super();
    }

    public ArmChair findArmChairById(int pId){
        return crudService.find(ArmChair.class, pId);
    }

    public List<ArmChair> findAllArmChairs(){
        return (List<ArmChair>) crudService.findWithNamedQuery(ArmChair.GET_ALL);
    }
}
