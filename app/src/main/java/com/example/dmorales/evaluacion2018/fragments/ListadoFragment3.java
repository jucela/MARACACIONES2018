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
import com.example.dmorales.evaluacion2018.adapters.RegistradoAdapter1;
import com.example.dmorales.evaluacion2018.adapters.RegistradoAdapter2;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo1;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo2;
import com.example.dmorales.evaluacion2018.modelo.Data;
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
public class ListadoFragment3 extends Fragment {
    RecyclerView recyclerView;
    RegistradoAdapter2 registradoAdapter2;
    Context context;

    ArrayList<AsistenteModelo2> asistentes;
    ArrayList<AsistenteModelo2> asistentes1;
    ArrayList<AsistenteModelo2> asistentes2;
    String cod_local;
    String usuario;
    String sede;
    Data data;
    FloatingActionButton fabUpLoad;
    TextView txtNumero;
    boolean b = false;

    public ListadoFragment3() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public ListadoFragment3(String usuario,String cod_local, Context context) {
        this.context = context;
        this.cod_local = cod_local;
        this.usuario = usuario;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_listado2, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listado_recycler2);
        fabUpLoad = (FloatingActionButton) rootView.findViewById(R.id.listado_btnUpload2);
        txtNumero = (TextView) rootView.findViewById(R.id.listado_txtNumero2);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        cargaData();
        registradoAdapter2  = new RegistradoAdapter2(asistentes,context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(registradoAdapter2);

        fabUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = false;
                asistentes1 = new ArrayList<>();
                try {
                    data = new Data(context);
                    data.open();
                    asistentes1 = data.getAllEstado1Asistentes3();
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(asistentes1.size() > 0){
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    for (final AsistenteModelo2 asistenteModelo2 : asistentes1){
                        if(asistenteModelo2.getSubido1()==0) {
                            asistenteModelo2.setSubido1(1);
                            String coleccion = "ASISTENCIA_CAPACITACION_ECE2018";
                            String coleccion2 = "SINNIVEL";
                            WriteBatch batch = db.batch();
                            DocumentReference documentReference = db.collection(coleccion).document(coleccion2).collection("asistentes").document(asistenteModelo2.get_id());
                            batch.update(documentReference,"fecha_registro1", new Timestamp(new Date(asistenteModelo2.getAnio1(),asistenteModelo2.getMes1(),asistenteModelo2.getDia1(),asistenteModelo2.getHora1(),asistenteModelo2.getMinuto1())));
                            batch.update(documentReference,"estatus_registro1",asistenteModelo2.getEstatus1());
                            batch.update(documentReference,"hora_transferencia_registro1", FieldValue.serverTimestamp());
                            batch.update(documentReference,"usuario1", usuario);
                            final String c = asistenteModelo2.getNumdoc();
                            batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("FIRESTORE", "DocumentSnapshot successfully written!");
                                    if (!b) {
                                        //
                                        Toast.makeText(context, asistentes1.size() + "  Registros de Entrada en la Nube", Toast.LENGTH_SHORT).show();
                                        b = true;
                                    }
                                    try {
                                        data = new Data(context);
                                        data.open();
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put(SQLConstantes.fecha_de_registro_subido1, 1);
                                        data.actualizarAsistencia3(c, contentValues);
                                        cargaData();
                                        registradoAdapter2.notifyDataSetChanged();
                                        data.close();
                                        //actualizar colores
                                        cargaData();
                                        registradoAdapter2 = new RegistradoAdapter2(asistentes,context);
                                        recyclerView.setAdapter(registradoAdapter2);
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
//                cargaData();
//                registradoAdapter1 = new RegistradoAdapter1(asistentes,context);
//                recyclerView.setAdapter(registradoAdapter1);
            }
        });
    }

    public void cargaData(){
        asistentes = new ArrayList<>();
        try {
            Data data = new Data(context);
            data.open();
            asistentes = data.getAllAsistentes3(cod_local);
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
