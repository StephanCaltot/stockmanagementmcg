package fr.univtln.mcg.jsf;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.material.Material;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlng on 30/11/16.
 */
public class app {
    public static void main(String[] args) {
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
    }
}
