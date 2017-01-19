package fr.univtln.mcg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import fr.univtln.mcg.enums.ERoomTypes;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.technologic.Computer;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by marti on 02/12/2016.
 */
public class RestClient {
    public static void main(String[] args) throws IOException {

        Client c = Client.create();
        WebResource webResource = c.resource("http://62.210.72.150:8081");

        Room room  = new Room(1,"U026", ERoomTypes.TP);
        Room room2 = new Room(2,"T003", ERoomTypes.TD);
        Room room3 = new Room(3,"Y001", ERoomTypes.AMPHI);
        Room room4 = new Room(4,"W107", ERoomTypes.TD);

        Computer computer = Computer.builder().touch(true).brand(ETechnologicBrands.ASUS).room(room3).build();
        Computer computer2 = Computer.builder().touch(false).brand(ETechnologicBrands.ASUS).room(room4).build();

        //computer.setMTouch(true);
        //computer.setMBrand(ETechnologicBrands.EPSON);
        //computer.setMRoom(room3);

        //Computer computer2 = new Computer();
        //computer2.setMTouch(true);
        //computer2.setMBrand(ETechnologicBrands.ASUS);
        //computer2.setMRoom(room2);

        webResource.path("stockmanagementwebservices/webresources/rooms").type(MediaType.APPLICATION_JSON).post(room);
        webResource.path("stockmanagementwebservices/webresources/rooms").type(MediaType.APPLICATION_JSON).post(room2);
        webResource.path("stockmanagementwebservices/webresources/rooms").type(MediaType.APPLICATION_JSON).post(room3);
        webResource.path("stockmanagementwebservices/webresources/rooms").type(MediaType.APPLICATION_JSON).post(room4);

        webResource.path("stockmanagementwebservices/webresources/computers").type(MediaType.APPLICATION_JSON).post(computer);
        webResource.path("stockmanagementwebservices/webresources/computers").type(MediaType.APPLICATION_JSON).post(computer2);


        /*String json = webResource.path("stockmanagementwebservices/webresources/materials/a").type(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(json);

        ObjectMapper mapper = new ObjectMapper();
        List<Material> materialsGotten = mapper.readValue(json, new TypeReference<List<Material>>(){});
        System.out.println(materialsGotten);*/
       /* ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithType(new TypeReference<List<Material>>() {
        }).writeValueAsString(materials);
        System.out.println("json: \n" + json);
    */}
}
