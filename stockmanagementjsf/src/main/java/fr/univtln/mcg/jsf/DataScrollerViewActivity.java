package fr.univtln.mcg.jsf;

import fr.univtln.mcg.Activity;
import fr.univtln.mcg.material.Material;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jlng on 29/11/16.
 */

@ManagedBean
@ViewScoped
public class DataScrollerViewActivity implements Serializable {

    private List<Activity> activities;

    @ManagedProperty("#{activityService}")
    private ActivityService service;

    @PostConstruct
    public void init() {
        activities = service.create();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setService(ActivityService service) {
        this.service = service;
    }
}
