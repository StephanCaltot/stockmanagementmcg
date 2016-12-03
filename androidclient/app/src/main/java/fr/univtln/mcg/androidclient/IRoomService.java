package fr.univtln.mcg.androidclient;

import java.util.List;

import fr.univtln.mcg.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marti on 29/11/2016.
 */

public interface IRoomService {

    @GET("/stockmanagementwebservices/webresources/rooms/{id}")
    Call<Room> get(@Path("id") int id);

    @GET("/stockmanagementwebservices/webresources/rooms")
    Call<List<Room>> getAll();
}
