package fr.univtln.mcg.business;

import fr.univtln.mcg.ActivityLog;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marti on 28/11/2016.
 */

@Stateless
public class ActivityLogManagerBean extends GenericManagerBean<ActivityLog> {

    public ActivityLogManagerBean(){
        super();
    }

    public ActivityLog findActivityLogById(int pId){
        return crudService.find(ActivityLog.class, pId);
    }

    public List<ActivityLog> findAllActivitiesLog(){
        return (List<ActivityLog>) crudService.findWithNamedQuery(ActivityLog.GET_ALL);
    }
}
