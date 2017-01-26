package fr.univtln.mcg.business;

import fr.univtln.mcg.Person;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
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
