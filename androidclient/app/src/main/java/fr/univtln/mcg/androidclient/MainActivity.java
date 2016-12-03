package fr.univtln.mcg.androidclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.univtln.mcg.Room;


public class MainActivity extends Activity{

    private Presenter presenter;
    @BindView(R.id.textview) TextView roomTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        ButterKnife.bind(this);
        roomTextView.setText("cliquez sur bouton");
    }

    public void displayRoomGottenById(Room room){
        String lMessage = "room id : " + room.getId() + "\nroom name" + room.getName();
        Toast.makeText(MainActivity.this, lMessage, Toast.LENGTH_LONG).show();
        roomTextView.setText(lMessage);
    }

    public void displayFailureInRoomGottenById(){
        Toast.makeText(MainActivity.this, "failure in room gotten by id", Toast.LENGTH_LONG).show();
    }

    public void pressButton(View view){
        presenter.getRoomById(1);
    }

}
