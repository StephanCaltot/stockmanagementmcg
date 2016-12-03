package fr.univtln.mcg.androidclient;

import java.util.List;

import fr.univtln.mcg.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marti on 03/12/2016.
 */

public interface IMaterialService {
    @GET("/stockmanagementwebservices/webresources/materials/{id}")
    Call<Room> get(@Path("id") int id);

    @GET("/stockmanagementwebservices/webresources/materials/all")
    Call<String> getAll();
}
