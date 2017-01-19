package com.example.screetts.stockmanagement.retrofit;

import java.util.List;

import fr.univtln.mcg.ActivityLog;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jlng on 17/01/17.
 */

public interface IAssignationService {

    @GET("/stockmanagementwebservices/webresources/activitylogs/nongen/{id_activity_log}")
    Call<ActivityLog> get(@Path("id_activity_log") int id);

    @GET("/stockmanagementwebservices/webresources/activitylogs/nongen")
    Call<List<ActivityLog>> getAll();

    @POST("/stockmanagementwebservices/webresources/activitylogs/")
    Call<List<ActivityLog>> create();
}
