package fr.univtln.mcg.jsf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.technologic.Computer;
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
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/computers");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        System.out.println(value);

        target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/rooms");
        response = target.request().get();
        String value2 = response.readEntity(String.class);
        System.out.println(value);


        // JACKSON
        ObjectMapper mapper = new ObjectMapper();
        List<Material> list = null;
        List<Room> li = new ArrayList<>();
        try {
            list = mapper.readValue(value, mapper.getTypeFactory().constructCollectionType(List.class, Material.class));
            //li = mapper.readValue(value2, new TypeReference<List<Room>>() {});
            //Computer c = mapper.readValue(value, Computer.class);

            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.close();
        //System.out.println(((Computer)list.get(1).getMMateriels().get(0)).getMBrand());
    }
}
