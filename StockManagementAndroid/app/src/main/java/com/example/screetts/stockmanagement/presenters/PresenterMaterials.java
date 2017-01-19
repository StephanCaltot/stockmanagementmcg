package com.example.screetts.stockmanagement.presenters;

import android.util.Log;

import com.example.screetts.stockmanagement.fragment.MaterialsFragment;
import com.example.screetts.stockmanagement.retrofit.IMaterialsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import fr.univtln.mcg.material.Material;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by marti on 30/11/2016.
 */

public class PresenterMaterials {

    public static final String BASE_URL = "http://192.168.43.73:8080";
    private MaterialsFragment view;
    private List<Material> materials;
    private ObjectMapper objectMapper = new ObjectMapper();
    Retrofit retrofit;
    IMaterialsService materialsService;

    public PresenterMaterials(){}

    public PresenterMaterials(MaterialsFragment view){
        this.view = view;
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        this.materialsService = retrofit.create(IMaterialsService.class);
    }

    public void getMaterialsById(int id){
        Call<Material> call = materialsService.get(id);
        call.enqueue(new Callback<Material>() {
            @Override
            public void onResponse(Call<Material> call, Response<Material> response) {
                if (response.body() != null){
                    view.displayMaterialsGottenById(response.body());
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<Material> call, Throwable t) {
                view.displayFailureInMaterialsGottenById();
            }
        });
    }


    public List<Material> getMaterialsAll(){

        Call<ResponseBody> call = materialsService.getAll();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body() != null){

                    try {

                        materials = objectMapper.readValue(response.body().string(), new TypeReference<List<Material>>() {});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    view.displayMaterialsAll(materials);
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("error",t.getMessage());
                view.displayFailureInMaterialsAll();
            }
        });
        return materials;
    }

}
