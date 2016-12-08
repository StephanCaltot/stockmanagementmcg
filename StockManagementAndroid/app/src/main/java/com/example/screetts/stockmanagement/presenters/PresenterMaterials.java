package com.example.screetts.stockmanagement.presenters;

import android.util.Log;

import com.example.screetts.stockmanagement.fragment.MaterialsFragment;
import com.example.screetts.stockmanagement.retrofit.IMaterialsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import fr.univtln.mcg.material.Material;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by marti on 30/11/2016.
 */

public class PresenterMaterials {

    public static final String BASE_URL = "http://10.21.143.57:8080";
    private MaterialsFragment view;
    private List<Material> materials;
    private Material material;
    private ObjectMapper objectMapper;
    Retrofit retrofit;
    IMaterialsService materialsService;

    public PresenterMaterials(){}

    public PresenterMaterials(MaterialsFragment view){
        this.view = view;
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        this.materialsService = retrofit.create(IMaterialsService.class);
    }

    public void getMaterialsById(int id){
        objectMapper = new ObjectMapper();
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



    public void getMaterialsAll(){
        Call<String> call = materialsService.getAll();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null){
//                    materials = objectMapper.readValues(response.body(), String);
                    view.displayMaterialsAll(materials);
                }
                else{
                    Log.i("response", "body response null");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.displayFailureInMaterialsAll();
            }
        });
    }

}
