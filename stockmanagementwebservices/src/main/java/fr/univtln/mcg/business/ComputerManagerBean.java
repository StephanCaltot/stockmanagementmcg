package fr.univtln.mcg.business;

import fr.univtln.mcg.material.technologic.Computer;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 07/12/2016.
 */

@Stateless
public class ComputerManagerBean extends GenericManagerBean<Computer> {

    public ComputerManagerBean() {
        super();
    }

    public Computer findComputerById(int pId){
        return crudService.find(Computer.class, pId);
    }

    public List<Computer> findAllComputers(){
        return (List<Computer>) crudService.findWithNamedQuery(Computer.GET_ALL_COMPUTER);
    }
}
