package fr.univtln.mcg.business;

import fr.univtln.mcg.material.pedagogic.Educational;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 07/12/2016.
 */

@Stateless
public class EducationalManagerBean extends GenericManagerBean<Educational> {

    public EducationalManagerBean(){
        super();
    }

    public Educational findEducationalById(int id){
        return crudService.find(Educational.class, id);
    }

    public List<Educational> findAllEducationals(){
        return (List<Educational>) crudService.findWithNamedQuery(Educational.GET_ALL_EDUCATIONAL);
    }
}
