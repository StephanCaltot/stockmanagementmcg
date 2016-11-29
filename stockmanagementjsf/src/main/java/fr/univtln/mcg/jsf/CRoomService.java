package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 29/11/16.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.univtln.mcg.CRoom;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;
import java.util.List;

@ManagedBean(name = "roomService")
@ApplicationScoped
public class CRoomService {

    public List<CRoom> createRooms() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/room");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        Gson gson = new Gson();
        List<CRoom> list = gson.fromJson(value, new TypeToken<List<CRoom>>(){}.getType());
        response.close();
        return list;
    }

}