package com.example.screetts.stockmanagement.retrofit;

import java.util.List;

import fr.univtln.mcg.material.Material;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marti on 29/11/2016.
 */

public interface IMaterialsService {

    @GET("/stockmanagementwebservices/webresources/materials/{id_material}")
    Call<Material> get(@Path("id_material") int id);

    @GET("/stockmanagementwebservices/webresources/materials/all")
    Call<ResponseBody> getAll();
}
