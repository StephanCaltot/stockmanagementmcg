package fr.univtln.mcg.jsf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.univtln.mcg.CRoom;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public class app {

    public static void main(String[] args) throws IOException {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/stockmanagementwebservices/webresources/room");
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        System.out.println(value);
        Gson gson = new Gson();
        List<CRoom> videos = gson.fromJson(value, new TypeToken<List<CRoom>>(){}.getType());
        response.close();

    }
}