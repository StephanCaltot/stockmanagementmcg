package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 29/11/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.Material;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "materialService")
@ApplicationScoped
public class MaterialService {

    public List<Material> createMaterials()  {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/materials/all");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Material> list = new ArrayList<>();
        try {
            list = mapper.readValue(value, mapper.getTypeFactory().constructCollectionType(List.class, Material.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.close();
        return list;
    }

}