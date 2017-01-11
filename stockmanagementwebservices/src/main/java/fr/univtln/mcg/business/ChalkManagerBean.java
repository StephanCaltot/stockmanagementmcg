package fr.univtln.mcg.business;

import fr.univtln.mcg.material.pedagogic.Chalk;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 08/12/2016.
 */

@Stateless
public class ChalkManagerBean extends GenericManagerBean<Chalk> {

    public ChalkManagerBean(){
        super();
    }

    public Chalk findChalkById(int pId){
        return crudService.find(Chalk.class, pId);
    }

    public List<Chalk> findAllChalks(){
        return (List<Chalk>) crudService.findWithNamedQuery(Chalk.GET_ALL);
    }

}
