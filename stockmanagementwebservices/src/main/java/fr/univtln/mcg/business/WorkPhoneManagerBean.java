package fr.univtln.mcg.business;

import fr.univtln.mcg.material.technologic.WorkPhone;

import javax.ejb.Stateless;
import javax.resource.spi.work.Work;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

/**
 * Business class for the WorkPhones.
 * Provides way to do the basic CRUD operations
 * on the WorkPhones class.
 */

@Stateless
public class WorkPhoneManagerBean extends GenericManagerBean<WorkPhone> {

    public WorkPhoneManagerBean(){
        super();
    }

    public WorkPhone findWorkPhoneById(int pId){
        return crudService.find(WorkPhone.class, pId);
    }

    public List<WorkPhone> findAllWorkPhones(){
        return (List<WorkPhone>) crudService.findWithNamedQuery(WorkPhone.GET_ALL);
    }

}
