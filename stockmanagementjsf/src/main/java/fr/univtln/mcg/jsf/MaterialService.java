package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 29/11/16.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.univtln.mcg.material.Material;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;
import java.util.List;

@ManagedBean(name = "materialService")
@ApplicationScoped
public class MaterialService {

    public List<Material> createMaterials() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/materials");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        Gson gson = new Gson();
        List<Material> list = gson.fromJson(value, new TypeToken<List<Material>>(){}.getType());
        response.close();
        return list;
    }

}