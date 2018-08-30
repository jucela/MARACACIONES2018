package com.example.dmorales.evaluacion2018.fragments;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.adapters.RegistradoAdapter;
import com.example.dmorales.evaluacion2018.adapters.RegistradoAdapter1;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo1;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.Registrado;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoFragment1 extends Fragment {
    RecyclerView recyclerView;
    RegistradoAdapter1 registradoAdapter1;
    Context context;
    ArrayList<Registrado> registrados;
    ArrayList<Registrado> agregados;
    ArrayList<Registrado> agregados2;
    ArrayList<Registrado> agregados3;
    ArrayList<Registrado> agregados4;

    ArrayList<AsistenteModelo1> asistentes;
    ArrayList<AsistenteModelo1> asistentes1;
    ArrayList<AsistenteModelo1> asistentes2;
    String cod_local;
    String sede;
    Data data;
    FloatingActionButton fabUpLoad;
    TextView txtNumero;
    boolean b = false;

    public ListadoFragment1() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public ListadoFragment1(String cod_local, Context context) {
        this.context = context;
        this.cod_local = cod_local;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_listado, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listado_recycler);
        fabUpLoad = (FloatingActionButton) rootView.findViewById(R.id.listado_btnUpload);
        txtNumero = (TextView) rootView.findViewById(R.id.listado_txtNumero);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        cargaData();
        registradoAdapter1 = new RegistradoAdapter1(asistentes,context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(registradoAdapter1);

        fabUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = false;
                agregados = new ArrayList<>();
                agregados2 = new ArrayList<>();
                try {
                    data = new Data(context);
                    data.open();
                    asistentes1 = data.getAllEstado1Asistentes1();
                    asistentes2 = data.getAllEstado2Asistentes1();
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(asistentes.size() > 0){
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    for (final AsistenteModelo1 asistenteModelo1 : asistentes){
                        if(asistenteModelo1.getSubido1()==0 && asistenteModelo1.getSubido2()==0) {
                            asistenteModelo1.setSubido1(1);
                            String coleccion = "ASISTENCIA_CAPACITACION_ECE2018";
                            WriteBatch batch = db.batch();
                            DocumentReference documentReference = db.collection(coleccion).document(asistenteModelo1.getNumdoc());
                            batch.update(documentReference,"fecha_registro1", new Timestamp(new Date(asistenteModelo1.getAnio1(),asistenteModelo1.getMes1(),asistenteModelo1.getDia1(),asistenteModelo1.getHora1(),asistenteModelo1.getMinuto1())));
                            batch.update(documentReference,"estatus1",asistenteModelo1.getEstatus1());
                            batch.update(documentReference,"hora_transferencia_entrada", FieldValue.serverTimestamp());
                            final String c = asistenteModelo1.getNumdoc();
                            batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("FIRESTORE", "DocumentSnapshot successfully written!");
                                    if (!b) {
                                        //
                                        Toast.makeText(context, asistentes.size() + "  Registros de Entrada en la Nube", Toast.LENGTH_SHORT).show();
                                        b = true;
                                    }
                                    try {
                                        data = new Data(context);
                                        data.open();
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put(SQLConstantes.fecha_de_registro_subido1, 1);
                                        data.actualizarAsistencia1(c, contentValues);
                                        cargaData();
                                        registradoAdapter1.notifyDataSetChanged();
                                        data.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FIRESTORE", "Error writing document", e);
                                }
                            });

                        }//end if
                        else {Toast.makeText(context, " Error al cargar subidos", Toast.LENGTH_SHORT).show();}
                    }//end for
                }else if(asistentes2.size() > 0){
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    for (final AsistenteModelo1 asistente : asistentes2){
                        if(asistente.getSubido1()==1 && asistente.getSubido2()==0) {
                            asistente.setSubido2(1);
                            String coleccion = "ASISTENCIA_CAPACITACION_ECE2018";
                            WriteBatch batch = db.batch();
                            DocumentReference documentReference = db.collection(coleccion).document(asistente.getNumdoc());
                            batch.update(documentReference,"fecha_registro2", new Timestamp(new Date(asistente.getAnio2(),asistente.getMes2(),asistente.getDia2(),asistente.getHora2(),asistente.getMinuto2())));
                            batch.update(documentReference,"estatus2",asistente.getEstatus2());
                            batch.update(documentReference,"hora_transferencia_salida", FieldValue.serverTimestamp());
                            final String c = asistente.getNumdoc();

                            batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("FIRESTORE", "DocumentSnapshot successfully written!");
                                    if (!b) {
                                        //
                                        Toast.makeText(context, agregados.size() + "  Registros de Entrada en la Nube", Toast.LENGTH_SHORT).show();
                                        b = true;
                                    }
                                    try {
                                        data = new Data(context);
                                        data.open();
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put(SQLConstantes.fecha_de_registro_subido2, 1);
                                        data.actualizarFechaRegistro(c, contentValues);
                                        cargaData();
                                        registradoAdapter1.notifyDataSetChanged();
                                        data.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FIRESTORE", "Error writing document", e);
                                }
                            });

                        }//end if
                        else {Toast.makeText(context, " Error al cargar subidos", Toast.LENGTH_SHORT).show();}
                    }//end for
                }else {
                    Toast.makeText(context, "No hay registros nuevos para subir", Toast.LENGTH_SHORT).show();
                }
                cargaData();
                registradoAdapter1 = new RegistradoAdapter1(asistentes,context);
                recyclerView.setAdapter(registradoAdapter1);
            }
        });
    }

    public void cargaData(){
        asistentes = new ArrayList<>();
        try {
            Data data = new Data(context);
            data.open();
            asistentes = data.getAllAsistentes1(cod_local);
            txtNumero.setText("Total registros: " + asistentes.size());
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
