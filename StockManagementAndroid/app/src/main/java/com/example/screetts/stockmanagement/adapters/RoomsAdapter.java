package com.example.screetts.stockmanagement.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import fr.univtln.mcg.Room;
import fr.univtln.mcg.androidclient.R;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.MyViewHolder> {

    private List<Room> roomsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_room, id_room, type_room;

        public MyViewHolder(View view) {
            super(view);
            name_room = (TextView) view.findViewById(R.id.name_room);
            id_room = (TextView) view.findViewById(R.id.id_room);
            type_room = (TextView) view.findViewById(R.id.type_room);
        }
    }


    public void setRoomsList(List<Room> roomsList) {
        this.roomsList = roomsList;
    }

    public RoomsAdapter(List<Room> roomsList) {
        this.roomsList = roomsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rooms_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Room room = roomsList.get(position);
        holder.name_room.setText(room.getName());
        holder.id_room.setText(Integer.toString(room.getId()));
        holder.type_room.setText(room.getType().toString());
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }
}
