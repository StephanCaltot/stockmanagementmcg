package fr.univtln.mcg.jsf;

/**
 * Created by jlng on 29/11/16.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.Material;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;


@ManagedBean(name = "materialService")
@ApplicationScoped
public class MaterialService implements Serializable {

    private static Logger logger = Logger.getLogger(String.valueOf(MaterialService.class));


    private List<Material> list;

    public List<Material> create() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/materials/nongen");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        // JACKSON
        ObjectMapper mapper = new ObjectMapper();
        list = null;
        try {
            list = mapper.readValue(value, new TypeReference<List<Material>>(){});
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }
        response.close();
        return list;
    }


    public void update(Material material, Room room) {
        material.setRoom(room);
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);
        Client c = Client.create(cc);
        WebResource webResource = c.resource("http://localhost:8080/stockmanagementwebservices/webresources/");

        webResource.path("materials").type(MediaType.APPLICATION_JSON).put(material);
    }

    public List<Material> getList() {
        return list;
    }
}