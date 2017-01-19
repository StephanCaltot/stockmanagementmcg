package com.example.screetts.stockmanagement.presenters;

import android.util.Log;

import com.example.screetts.stockmanagement.fragment.AssignationFragment;
import com.example.screetts.stockmanagement.retrofit.IAssignationService;
import com.example.screetts.stockmanagement.retrofit.IMaterialsService;
import com.example.screetts.stockmanagement.retrofit.IRoomService;

import fr.univtln.mcg.ActivityLog;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.material.Material;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by jlng on 17/01/17.
 */

public class PresenterAssignation {

    public static final String BASE_URL = "http://192.168.43.73:8080";
    private AssignationFragment view;
    Retrofit retrofit;
    IAssignationService assignationService;
    IRoomService roomService;
    IMaterialsService materialsService;

    private ActivityLog activityLog;
    private Room room;
    private Material material;

    public PresenterAssignation(){}

    public PresenterAssignation(AssignationFragment view){
        this.view = view;
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        this.assignationService = retrofit.create(IAssignationService.class);
        this.roomService = retrofit.create(IRoomService.class);
        this.materialsService = retrofit.create(IMaterialsService.class);

    }
/*
    public ActivityLog createActivityLog(int id){
        Call<ActivityLog> call = assignationService.get(id);
        call.enqueue(new Callback<ActivityLog>() {
            @Override
            public void onResponse(Call<ActivityLog> call, Response<ActivityLog> response) {
                if (response.body() != null){
                    activityLog = response.body();
                    view.displayRoomGottenById(response.body());
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<ActivityLog> call, Throwable t) {
            }
        });
        return activityLog;
    }
*/
    public Room getRoomById(int id, int nb){
        Call<Room> call = roomService.get(id);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.body() != null){
                    room = response.body();
                    if (nb == 0) {
                        view.setActualRoom(room);
                    }
                    else if (nb == 2)
                    {
                        view.setNewRoom(room);
                    }
                    //view.displayRoomGottenById(response.body());
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
            }
        });
        return room;
    }

    public Material getMaterialById(int id){
        Call<Material> call = materialsService.get(id);
        call.enqueue(new Callback<Material>() {
            @Override
            public void onResponse(Call<Material> call, Response<Material> response) {
                if (response.body() != null){
                    material = response.body();
                    Log.i("TEST", "TTTTTTTTTT" + room.getId()+"   "+material.getRoom().getId());
                    if(room.getId() != material.getRoom().getId())
                    {
                        Log.i("TEST", "Erreur: le materiel ne se trouve pas dans la bonne salle");
                        view.setErrorMaterial(material);
                    }
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<Material> call, Throwable t) {
            }
        });
        return material;
    }

    public void getInfoFromQrCode(String qrInfo, int nb)
    {
        Log.i("TEST", qrInfo + "     "+ nb);

        String [] split = qrInfo.split(":");
        Log.i("TEST", String.valueOf(split));
        int id = Integer.parseInt(split[1]);
        if (split[0].equals("Room"))
        {
            getRoomById(id, nb);
        }
        else if(split[0].equals("Material"))
        {
            room = view.getActualRoom();
            getMaterialById(id);
        }
    }

}
