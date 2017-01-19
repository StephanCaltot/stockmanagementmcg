package fr.univtln.mcg.jsf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.Activity;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jlng on 19/01/17.
 */
@ManagedBean(name = "activityService")
@ApplicationScoped
public class ActivityService implements Serializable {

    private List<Activity> list;

    public List<Activity> create() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/activities/nongen");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        // JACKSON
        ObjectMapper mapper = new ObjectMapper();
        list = null;
        try {
            list = mapper.readValue(value, new TypeReference<List<Activity>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.close();
        return list;
    }


    public List<Activity> getList() {
        return list;
    }
}