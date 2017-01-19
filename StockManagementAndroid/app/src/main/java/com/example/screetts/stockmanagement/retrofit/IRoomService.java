package com.example.screetts.stockmanagement.retrofit;

import java.util.List;

import fr.univtln.mcg.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marti on 29/11/2016.
 */

public interface IRoomService {

    @GET("/stockmanagementwebservices/webresources/rooms/nongen/{id_room}")
    Call<Room> get(@Path("id_room") int id);

    @GET("/stockmanagementwebservices/webresources/rooms/nongen")
    Call<List<Room>> getAll();
}
