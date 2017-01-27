package fr.univtln.mcg.business;

import fr.univtln.mcg.Person;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.business.GenericManagerBean;
import fr.univtln.mcg.material.Material;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

/**
 * Business class for the People.
 * Provides way to do the basic CRUD operations
 * on the Person class.
 */

@Stateless
public class PersonManagerBean extends GenericManagerBean<Person> {


    public PersonManagerBean(){
        super();
    }

    public Person findPersonById(int pId){
        return crudService.find(Person.class, pId);
    }

    public List<Person> findAllPeople(){
        return (List<Person>)crudService.findWithNamedQuery(Person.GET_ALL);
    }


}
