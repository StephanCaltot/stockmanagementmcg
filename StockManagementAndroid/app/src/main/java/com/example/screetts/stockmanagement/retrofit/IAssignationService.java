package com.example.screetts.stockmanagement.retrofit;

import java.util.List;

import fr.univtln.mcg.Activity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jlng on 17/01/17.
 */

public interface IAssignationService {

    @GET("/stockmanagementwebservices/webresources/activities/nongen/{id_activity}")
    Call<Activity> get(@Path("id_activity") int id);

    @GET("/stockmanagementwebservices/webresources/activities/nongen")
    Call<List<Activity>> getAll();

    @POST("/stockmanagementwebservices/webresources/activities/")
    Call<Activity> create(@Body Activity activity);
}
