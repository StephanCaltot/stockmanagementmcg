package com.example.screetts.stockmanagement.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.screetts.stockmanagement.presenters.PresenterAssignation;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import fr.univtln.mcg.Room;
import fr.univtln.mcg.androidclient.R;
import fr.univtln.mcg.material.Material;


public class AssignationFragment extends Fragment {


    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;

    private Button assign;

    private Context thiscontext;
    private String value = "";
    private int nb = 0;
    private PresenterAssignation presenterAssignation;
    private Room actualRoom;
    private Room newRoom;
    private Material material;

    // New room
    private TextView idNewRoom;
    private TextView nameNewRoom;
    private TextView typeNewRoom;
    // Actual room
    private TextView idRoom;
    private TextView nameRoom;
    private TextView typeRoom;
    // Material
    private TextView idMaterial;
    private TextView brandMaterial;


    public AssignationFragment() {}

    public static AssignationFragment newInstance(String param1, String param2) {
        AssignationFragment fragment = new AssignationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterAssignation = new PresenterAssignation(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiscontext = container.getContext();

        View view = inflater.inflate(R.layout.assign, container, false);

            cameraView = (SurfaceView) view.findViewById(R.id.camera_view);

            nameRoom = (TextView) view.findViewById(R.id.nameRoom);
            brandMaterial = (TextView) view.findViewById(R.id.brandMaterial);
            nameNewRoom = (TextView) view.findViewById(R.id.nameNewRoom);

            idRoom = (TextView) view.findViewById(R.id.idRoom);
            idMaterial = (TextView) view.findViewById(R.id.idMaterial);
            idNewRoom = (TextView) view.findViewById(R.id.idNewRoom);

            typeNewRoom = (TextView) view.findViewById(R.id.typeNewRoom);
            typeRoom = (TextView) view.findViewById(R.id.typeRoom);

            assign = (Button) view.findViewById(R.id.assign_button);

            assign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenterAssignation.getInfoFromQrCode(value, nb);
                    nb = nb + 1;
                    if (nb > 2)
                        nb = 0;
                }
            });

            barcodeDetector =
                    new BarcodeDetector.Builder(thiscontext)
                            .setBarcodeFormats(Barcode.QR_CODE)
                            .build();

            cameraSource = new CameraSource
                    .Builder(thiscontext, barcodeDetector)
                    .setRequestedPreviewSize(640, 480)
                    .build();

            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(thiscontext, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
                @Override
                public void release() {
                }

                @Override
                public void receiveDetections(Detector.Detections<Barcode> detections) {
                    final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                    if (barcodes.size() != 0 ) {
                        if (nb == 0) {
                            nameRoom.post(new Runnable() {    // Use the post method of the TextView
                                public void run() {
                                    value = barcodes.valueAt(0).displayValue;
                                    idRoom.setText(value);
                                }
                            });
                        }
                        else if (nb == 1)
                        {
                            idMaterial.post(new Runnable() {    // Use the post method of the TextView
                                public void run() {
                                    value = barcodes.valueAt(0).displayValue;
                                    idMaterial.setText(value);
                                }
                            });
                        }
                        else if (nb == 2)
                        {
                            nameNewRoom.post(new Runnable() {    // Use the post method of the TextView
                                public void run() {
                                    value = barcodes.valueAt(0).displayValue;
                                    idNewRoom.setText(value);
                                }
                            });
                        }
                    }
                }
            });
        return view;
        }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setMaterial(Material material)
    {
        this.material = material;
        idMaterial.setText(String.valueOf(material.getId()));
    }

    public void setMaterialUpdate(Material material) {
        this.material = material;
        String lMessage = "Déplacement réalisé";
        Toast.makeText(getActivity(), lMessage, Toast.LENGTH_LONG).show();

        Fragment fragment = new MaterialsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    public void setErrorMaterial(Material material)
    {
        String lMessage = "Material " + material.getId() + " is in wrong room "+ material.getRoom().getName()
                + ". An activity log will be create.";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(lMessage).setTitle("Erreur");

        AlertDialog dialog = builder.create();
        dialog.show();
        Fragment fragment = new MaterialsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    public void setNewRoom(Room newRoom) {
        this.newRoom = newRoom;
        String lMessage = "room id_room : " + newRoom.getId() + "\nroom name" + newRoom.getName();
        Toast.makeText(getActivity(), lMessage, Toast.LENGTH_LONG).show();
        idNewRoom.setText(String.valueOf(newRoom.getId()));
        nameNewRoom.setText(newRoom.getName());
        typeNewRoom.setText(""+newRoom.getType());
    }

    public void setActualRoom(Room actualRoom) {
        this.actualRoom = actualRoom;
        String lMessage = "room id_room : " + actualRoom.getId() + "\nroom name" + actualRoom.getName();
        Toast.makeText(getActivity(), lMessage, Toast.LENGTH_LONG).show();
        idRoom.setText(String.valueOf(actualRoom.getId()));
        nameRoom.setText(actualRoom.getName());
        typeRoom.setText(""+actualRoom.getType());
    }

    public Room getActualRoom() {
        return actualRoom;
    }
}
