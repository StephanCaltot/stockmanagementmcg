package fr.univtln.mcg.business;

import fr.univtln.mcg.Activity;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

/**
 * Business class for the Activities.
 * Provides way to do the basic CRUD operations
 * on the Activity class.
 */

@Stateless
public class ActivityManagerBean extends GenericManagerBean<Activity> {

    public ActivityManagerBean(){
        super();
    }

    public Activity findActivityById(int pId){
        return crudService.find(Activity.class, pId);
    }

    public List<Activity> findAllActivities(){
        return (List<Activity>) crudService.findWithNamedQuery(Activity.GET_ALL);
    }
}
