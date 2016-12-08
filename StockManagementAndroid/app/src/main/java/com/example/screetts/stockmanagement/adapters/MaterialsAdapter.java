package com.example.screetts.stockmanagement.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import fr.univtln.mcg.androidclient.R;
import fr.univtln.mcg.material.Material;

public class MaterialsAdapter extends RecyclerView.Adapter<MaterialsAdapter.MyViewHolder> {

    private List<Material> materialList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView room, idMaterial;

        public MyViewHolder(View view) {
            super(view);
            idMaterial = (TextView) view.findViewById(R.id.material_id);
            room = (TextView) view.findViewById(R.id.material_room);
        }
    }


    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public MaterialsAdapter(List<Material> materialsList) {
        this.materialList = materialsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.materials_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Material material = materialList.get(position);
        holder.room.setText(material.getRoom().getName());
        holder.idMaterial.setText(Integer.toString(material.getId()));
    }

    @Override
    public int getItemCount(){
        return materialList.size();
    }
}
