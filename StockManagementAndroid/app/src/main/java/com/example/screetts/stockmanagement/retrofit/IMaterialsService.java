package com.example.screetts.stockmanagement.retrofit;

import fr.univtln.mcg.material.Material;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marti on 29/11/2016.
 */

public interface IMaterialsService {

    @GET("/stockmanagementwebservices/webresources/materials/nongen/{id_material}")
    Call<Material> get(@Path("id_material") int id);

    @GET("/stockmanagementwebservices/webresources/materials/nongen")
    Call<ResponseBody> getAll();
}
