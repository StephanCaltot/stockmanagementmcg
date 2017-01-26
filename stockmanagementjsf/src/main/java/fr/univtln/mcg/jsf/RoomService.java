package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 29/11/16.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.mcg.Room;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(name = "roomService")
@ApplicationScoped
public class RoomService {

    private static Logger logger = Logger.getLogger(String.valueOf(RoomService.class));

    private List<Room> list;

    public List<Room> create() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/rooms/nongen");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        // JACKSON
        ObjectMapper mapper = new ObjectMapper();
        list = null;
        try {
            list = mapper.readValue(value, new TypeReference<List<Room>>(){});
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }
        response.close();
        return list;
    }

    public List<Room> getList() {
        return list;
    }

}