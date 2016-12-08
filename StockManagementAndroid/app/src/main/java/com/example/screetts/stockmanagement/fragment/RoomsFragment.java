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

import com.example.screetts.stockmanagement.adapters.RoomsAdapter;
import com.example.screetts.stockmanagement.lists.RecyclerTouchListener;
import com.example.screetts.stockmanagement.presenters.PresenterRooms;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.mcg.Room;
import fr.univtln.mcg.androidclient.R;


public class RoomsFragment extends Fragment {

    private List<Room> roomsList = new ArrayList<>();
    private RoomsAdapter mAdapter;
    private PresenterRooms presenterRooms;
    private Context thiscontext;
    private RecyclerView recyclerView;

    public RoomsFragment() {}


    public static RoomsFragment newInstance(String param1, String param2) {
        RoomsFragment fragment = new RoomsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterRooms = new PresenterRooms(this);
        mAdapter = new RoomsAdapter(roomsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thiscontext = container.getContext();

        View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_rooms);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(thiscontext, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Room room = roomsList.get(position);
                Toast.makeText(thiscontext, room.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {
                Room room = roomsList.get(position);
                Toast.makeText(thiscontext,"Il y a " + room.getMateriels().size() + " liés à cette salle.", Toast.LENGTH_SHORT).show();
            }
        }));

        prepareRoomData();

        return view;
    }


    private void prepareRoomData() {
        presenterRooms.getRoomAll();
    }


    public void displayRoomGottenById(Room room){
        String lMessage = "room id_room : " + room.getId() + "\nroom name" + room.getName();
        Toast.makeText(getActivity(), lMessage, Toast.LENGTH_LONG).show();
    }

    public void displayRoomsAll(List<Room> rooms){
        roomsList = rooms;
        mAdapter.setRoomsList(roomsList);
        mAdapter.notifyDataSetChanged();
    }

    public void displayFailureInRoomGottenById(){
        Toast.makeText(getActivity(), "failure in room gotten by id_room", Toast.LENGTH_LONG).show();
    }

    public void displayFailureInRoomsAll(){
        Toast.makeText(getActivity(), "failure in rooms all", Toast.LENGTH_LONG).show();
    }

}
