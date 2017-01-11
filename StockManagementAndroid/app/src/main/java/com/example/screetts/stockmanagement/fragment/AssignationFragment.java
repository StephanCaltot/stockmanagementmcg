package com.example.screetts.stockmanagement.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import fr.univtln.mcg.androidclient.R;


public class AssignationFragment extends Fragment {


    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private TextView barcodeInfo;
    private TextView barcodeInfoMat;
    private TextView barcodeInfoNewRoom;
    private Button assign;

    private Context thiscontext;
    private int firstStep = 0;
    private int nb = 0;


    public AssignationFragment() {}

    public static AssignationFragment newInstance(String param1, String param2) {
        AssignationFragment fragment = new AssignationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiscontext = container.getContext();
        View view = inflater.inflate(R.layout.assign, container, false);

            cameraView = (SurfaceView) view.findViewById(R.id.camera_view);
            barcodeInfo = (TextView) view.findViewById(R.id.nameRoom);
            barcodeInfoMat = (TextView) view.findViewById(R.id.nameMaterial);
            barcodeInfoNewRoom = (TextView) view.findViewById(R.id.nameNewRoom);
            assign = (Button) view.findViewById(R.id.assign_button);

            assign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                            barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                                public void run() {
                                    barcodeInfo.setText(barcodes.valueAt(0).displayValue);
                                    firstStep = 1;
                                }
                            });
                        }
                        else if (nb == 1)
                        {
                            barcodeInfoMat.post(new Runnable() {    // Use the post method of the TextView
                                public void run() {
                                    barcodeInfoMat.setText(barcodes.valueAt(0).displayValue);
                                    firstStep = 2;
                                }
                            });
                        }
                        else if (nb == 2)
                        {
                            barcodeInfoNewRoom.post(new Runnable() {    // Use the post method of the TextView
                                public void run() {
                                    barcodeInfoNewRoom.setText(barcodes.valueAt(0).displayValue);
                                    firstStep = 2;
                                    Toast toast = Toast.makeText(thiscontext, "Hello", Toast.LENGTH_LONG);
                                    toast.show();
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

}
