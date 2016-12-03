package fr.univtln.mcg.androidclient;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import fr.univtln.mcg.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by marti on 30/11/2016.
 */

public class Presenter {

    public static final String BASE_URL = "http://62.210.72.150:8081/";
    private MainActivity view;
    Retrofit retrofit;
    IRoomService roomService;

    public Presenter(){}

    public Presenter(MainActivity view){
        this.view = view;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.roomService = retrofit.create(IRoomService.class);
    }

    public void getRoomById(int id){
        Call<Room> call = roomService.get(id);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.body() != null){
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
    }


}
