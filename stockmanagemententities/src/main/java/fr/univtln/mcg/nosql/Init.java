package fr.univtln.mcg.nosql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.enums.ERoomTypes;
import fr.univtln.mcg.enums.ETechnologicBrands;
import fr.univtln.mcg.material.Material;
import fr.univtln.mcg.material.technologic.Computer;

import java.net.UnknownHostException;


/**
 * Created by screetts on 04/11/16.
 */
public class Init {


    public Init() throws UnknownHostException {}


    /***
     * Converts any objetc in String
     * @param object
     * @return String "json"
     * @throws JsonProcessingException
     */
    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        String json = objMapper.writeValueAsString(object);
        return json;
    }


    public static void main(String[] args) throws Exception {

        /**
         * Set new local mongo client and get computer collection
         */

        DB db = new MongoClient( "localhost" , 27017 ).getDB("mongo4zoo");
        DBCollection computer = db.getCollection("computer");


        /**
         * Rooms's initialization
         */
        Room room = Room.builder("U026", ERoomTypes.TP).build();
        Room room2 = Room.builder("T003", ERoomTypes.TD).build();
        Room room3 = Room.builder("Y001", ERoomTypes.AMPHI).build();
        Room room4 = Room.builder("W107", ERoomTypes.TD).build();


        /**
         * Computer's initialization
         */
        Material computer1 = Computer.builder().touch(true).brand(ETechnologicBrands.ASUS).room(room3).build();
        Material computer2 = Computer.builder().touch(false).brand(ETechnologicBrands.MSI).room(room4).build();
        Material computer3 = Computer.builder().touch(false).brand(ETechnologicBrands.MAC).room(room).build();
        Material computer4 = Computer.builder().touch(true).brand(ETechnologicBrands.SONY).room(room2).build();


        /**
         * Document's initialization
         */
        BasicDBObject documentComputer = (BasicDBObject) JSON.parse(convertObjectToJson(computer1));
        BasicDBObject documentComputer2 = (BasicDBObject) JSON.parse(convertObjectToJson(computer2));
        BasicDBObject documentComputer3 = (BasicDBObject) JSON.parse(convertObjectToJson(computer3));
        BasicDBObject documentComputer4 = (BasicDBObject) JSON.parse(convertObjectToJson(computer4));


        /**
         * Inserts
         */
        computer.insert(documentComputer);
        computer.insert(documentComputer2);
        computer.insert(documentComputer3);
        computer.insert(documentComputer4);

    }
}