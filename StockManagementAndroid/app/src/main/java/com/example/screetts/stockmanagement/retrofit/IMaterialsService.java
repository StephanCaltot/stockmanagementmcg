package com.example.screetts.stockmanagement.retrofit;

import java.util.List;

import fr.univtln.mcg.material.Material;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marti on 29/11/2016.
 */

public interface IMaterialsService {

    @GET("/stockmanagementwebservices/webresources/materials/{id_room}")
    Call<Material> get(@Path("id_room") int id);

    @GET("/stockmanagementwebservices/webresources/materials")
    Call<String> getAll();
}
