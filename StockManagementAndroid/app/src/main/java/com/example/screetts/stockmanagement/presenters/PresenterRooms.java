package com.example.screetts.stockmanagement.presenters;

import android.util.Log;
import com.example.screetts.stockmanagement.fragment.RoomsFragment;
import com.example.screetts.stockmanagement.retrofit.IRoomService;
import java.util.List;
import fr.univtln.mcg.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by marti on 30/11/2016.
 */

public class PresenterRooms {

    public static final String BASE_URL = "http://10.21.143.57:8080";
    private RoomsFragment view;
    Retrofit retrofit;
    IRoomService roomService;
    private List<Room> rooms ;
    private Room room;

    public PresenterRooms(){}

    public PresenterRooms(RoomsFragment view){
        this.view = view;
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        this.roomService = retrofit.create(IRoomService.class);
    }

    public Room getRoomById(int id){
        Call<Room> call = roomService.get(id);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.body() != null){
                    room = response.body();
                    view.displayRoomGottenById(response.body());
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                view.displayFailureInRoomGottenById();
            }
        });
        return room;
    }


    public List<Room> getRoomAll(){
        Call<List<Room>> call = roomService.getAll();
        call.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.body() != null){
                    rooms = response.body();
                    view.displayRoomsAll(response.body());
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                view.displayFailureInRoomsAll();
            }
        });
        return rooms;
    }

}
