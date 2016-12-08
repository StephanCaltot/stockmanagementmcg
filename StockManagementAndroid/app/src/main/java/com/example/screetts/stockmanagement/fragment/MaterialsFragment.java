package com.example.screetts.stockmanagement.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.screetts.stockmanagement.adapters.MaterialsAdapter;
import com.example.screetts.stockmanagement.lists.RecyclerTouchListener;
import com.example.screetts.stockmanagement.presenters.PresenterMaterials;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.mcg.Room;
import fr.univtln.mcg.androidclient.R;
import fr.univtln.mcg.material.Material;



public class MaterialsFragment extends Fragment {

    private List<Material> materialsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MaterialsAdapter mAdapter;
    private Context thiscontext;
    private PresenterMaterials presenterMaterials;


    public MaterialsFragment() {}

    public static MaterialsFragment newInstance(String param1, String param2) {
        MaterialsFragment fragment = new MaterialsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterMaterials = new PresenterMaterials(this);
        mAdapter = new MaterialsAdapter(materialsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thiscontext = container.getContext();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_materials, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_materials);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(thiscontext, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Material material = materialsList.get(position);
                Toast.makeText(thiscontext, "Material numéro " + material.getId() + " is selected!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {
                Room room = materialsList.get(position).getRoom();
                Toast.makeText(thiscontext, "Il appartient à la salle " + room.getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        prepareMovieData();

        return view;
    }


    private void prepareMovieData() {presenterMaterials.getMaterialsAll();}


    public void displayMaterialsGottenById(Material material){
        String lMessage = "materials id_room : " + material.getId() + "\nmaterials room" + material.getRoom().getId();
        Toast.makeText(getActivity(), lMessage, Toast.LENGTH_LONG).show();
    }

    public void displayMaterialsAll(List<Material> materials){
        materialsList = materials;
        mAdapter.setMaterialList(materialsList);
        mAdapter.notifyDataSetChanged();
    }

    public void displayFailureInMaterialsAll(){
        Toast.makeText(getActivity(), "failure in materials all", Toast.LENGTH_LONG).show();
    }

    public void displayFailureInMaterialsGottenById(){
        Toast.makeText(getActivity(), "failure in materials gotten by id_room", Toast.LENGTH_LONG).show();
    }

}
