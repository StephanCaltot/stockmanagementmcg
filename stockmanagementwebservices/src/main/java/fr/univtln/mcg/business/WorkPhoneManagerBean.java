package fr.univtln.mcg.business;

import fr.univtln.mcg.material.technologic.WorkPhone;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
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
        return (List<WorkPhone>) crudService.findWithNamedQuery(WorkPhone.GET_ALL_WORK_PHONE);
    }

}
