package fr.univtln.mcg.nosql;

import com.mongodb.*;
import fr.univtln.mcg.material.technologic.Computer;

import java.net.UnknownHostException;


/**
 * Created by screetts on 04/11/16.
 */
public class Test {


    public Test() throws UnknownHostException {}


    public static void main(String[] args) throws Exception {

        /**
         * Set new local mongo client
         */
        DB db = new MongoClient( "localhost" , 27017 ).getDB("mongo4zoo");



        /**
         * Recover all database collections
         */
        DBCollection computer = db.getCollection("computer");
        DBCollection person = db.getCollection("person");
        DBCollection room = db.getCollection("room");



        /**
         * Search and print all persons wich has "guillon" as name
         */
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "guillon");

        DBCursor cursor = person.find(searchQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }



        /**
         * Changes the computer's brand
         */
        BasicDBObject query = new BasicDBObject();
        query.put("brand", "ASUS");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("brand", "ASUS");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        computer.update(query, updateObj);
        System.out.println(db.getCollection("computer").findOne(query));



        /***
         * Join request : all computers which have the room with id "3"
         */
        DBCursor lesOrdis = computer.find();
        while (lesOrdis.hasNext()) {
            Computer ordi = new CJsonDecoder<Computer>().Decoder(lesOrdis.next().toString(),Computer.class);
            if ( ordi.getRoom().getId() == 3) {
                System.out.println(ordi.getBrand().toString());
            }
        }
    }
}