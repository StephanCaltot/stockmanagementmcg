package fr.univtln.mcg.jsf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.enums.ERoomTypes;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.Material;

import fr.univtln.mcg.material.technologic.Computer;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by screetts on 30/11/16.
 */
public class clientrest {
    public static void main(String[] args) throws JsonProcessingException {
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);
        Client c = Client.create(cc);
        WebResource webResource = c.resource("http://localhost:8080/stockmanagementwebservices/webresources/");

        Room room  = new Room(1,"U026", ERoomTypes.TP,new ArrayList<Material>());
        Room room2 = new Room(2,"T003", ERoomTypes.TD,new ArrayList<Material>());
        Room room3 = new Room(3,"Y001", ERoomTypes.AMPHI,new ArrayList<Material>());
        Room room4 = new Room(4,"W107", ERoomTypes.TD,new ArrayList<Material>());

        Material computer = Computer.builder().touch(true).brand(ETechnologicBrands.ASUS).room(room3).build();
        Material computer2 = Computer.builder().touch(false).brand(ETechnologicBrands.ASUS).room(room4).build();

        //computer.setMTouch(true);
        //computer.setMBrand(ETechnologicBrands.EPSON);
        //computer.setMRoom(room3);

        //Computer computer2 = new Computer();
        //computer2.setMTouch(true);
        //computer2.setMBrand(ETechnologicBrands.ASUS);
        //computer2.setMRoom(room2);

        webResource.path("rooms").type(MediaType.APPLICATION_JSON).post(room);
        webResource.path("rooms").type(MediaType.APPLICATION_JSON).post(room2);
        webResource.path("rooms").type(MediaType.APPLICATION_JSON).post(room3);
        webResource.path("rooms").type(MediaType.APPLICATION_JSON).post(room4);

        webResource.path("computers").type(MediaType.APPLICATION_JSON).post(computer);
        webResource.path("computers").type(MediaType.APPLICATION_JSON).post(computer2);
        //List<Material> mat = new ArrayList<>();
        //String json =


       /* ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithType(new TypeReference<List<Material>>() {
        }).writeValueAsString(materials);
        System.out.println("json: \n" + json);
    */}
}
