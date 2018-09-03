package com.example.dmorales.evaluacion2018.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.adapters.RegistradoAdapter1;
import com.example.dmorales.evaluacion2018.adapters.RegistradoAdapter2;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo1;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo2;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.Registrado;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NubeFragment3 extends Fragment {
    RecyclerView recyclerView;
    Context context;
    ArrayList<AsistenteModelo2> asistentes;
    Spinner spdia;
    String sede;
    String cod_local;
    String aula;
    Button btn_buscar;
    TextView txtNumero;

    public NubeFragment3() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public NubeFragment3(String cod_local, Context context) {
        this.context = context;
        this.cod_local = cod_local;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nube2, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.nube_recycler2);
        txtNumero = (TextView) rootView.findViewById(R.id.nube_txtNumero2);
        return rootView;




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        cargaData();
        final RegistradoAdapter2 registradoAdapter2 = new RegistradoAdapter2(asistentes,context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(registradoAdapter2);


//        btn_buscar. setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cargaDataAula(spdia.getSelectedItem().toString());
//            }
//        });

    }

    public void cargaData(){
        asistentes = new ArrayList<>();
        try {
            Data data = new Data(context);
            data.open();
            asistentes = data.getAllNubeAsistentes3(cod_local);
            txtNumero.setText("Total enviados: " + asistentes.size());
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void cargaDataAula(String aula){
//        registrados = new ArrayList<>();
//        try {
//            Data data = new Data(context);
//            data.open();
//            registrados = data.getSedeRegistradosDia(aula);
//            txtNumero.setText("Total enviados: " + registrados.size());
//            data.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
