package com.example.dmorales.evaluacion2018.fragments;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmorales.evaluacion2018.NumericKeyBoardTransformationMethod;
import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo1;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.Nacional;

import java.io.IOException;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalidaLocalFragment2 extends Fragment {

    ImageView btnBuscar;
    EditText edtDni;

    CardView cvNoregistrado;
    CardView cvYaregistrado;
    CardView cvRegistro;
    CardView cvError;
    CardView cvAviso;
    CardView cvRBungalow;
    LinearLayout lyNBungalow;

    TextView txtErrorCargo;
    TextView txtErrorSede;
    TextView txtErrorLocal;

    TextView txtRegistroCargo;
    TextView txtRegistroDni;
    TextView txtRegistroNombres;
    TextView txtRegistroSede;
    TextView txtRegistroLocal;
    TextView txtRegistroAula;
    TextView txtRegistroNbungalow;
    TextView txtRegistroRbungalow;

    TextView txtRegistroDni_yaregistrado;
    TextView txtRegistroNombres_yaregistrado;
    TextView txtRegistroFecha;
    TextView txtRegistroDireccion;
    TextView txtErrorSede_error;
    TextView txtErrorLocal_error;

    String sede;
    String nro_local;
    Context context;



    public SalidaLocalFragment2() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public SalidaLocalFragment2(String nro_local, Context context) {
        this.nro_local = nro_local;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_salidalocal, container, false);

        btnBuscar = (ImageView) rootView.findViewById(R.id.salidalocal_btnBuscar);
        edtDni = (EditText) rootView.findViewById(R.id.salidalocal_edtDni);

        cvError = (CardView) rootView.findViewById(R.id.salidalocal_cvError);
        cvNoregistrado = (CardView) rootView.findViewById(R.id.salidalocal_cvNoRegistrado);
        cvRegistro = (CardView) rootView.findViewById(R.id.salidalocal_cvRegistro);
        cvYaregistrado = (CardView) rootView.findViewById(R.id.salidalocal_cvYaRegistrado);
        cvAviso = (CardView) rootView.findViewById(R.id.salidalocal_cvAviso);


        txtRegistroCargo = (TextView) rootView.findViewById(R.id.salidalocal_txtCargo);
        txtRegistroDni = (TextView) rootView.findViewById(R.id.salidalocal_txtDni);
        txtRegistroNombres = (TextView) rootView.findViewById(R.id.salidalocal_txtNombres);
        txtRegistroSede = (TextView) rootView.findViewById(R.id.salidalocal_txtSede);
        txtRegistroLocal = (TextView) rootView.findViewById(R.id.salidalocal_txtLocal);
        txtRegistroAula = (TextView) rootView.findViewById(R.id.salidalocal_txtAula);
        txtRegistroNbungalow = (TextView) rootView.findViewById(R.id.salidalocal_txtNbungalow);
        txtRegistroRbungalow = (TextView) rootView.findViewById(R.id.salidalocal_txtRbungalow);

        txtRegistroDni_yaregistrado = (TextView) rootView.findViewById(R.id.salidalocal_txtDni_yaregistrado);
        txtRegistroNombres_yaregistrado = (TextView) rootView.findViewById(R.id.salidalocal_txtNombres_yaregitstrado);
        txtRegistroFecha = (TextView) rootView.findViewById(R.id.salidalocal_txtfecha);


        txtErrorLocal_error = (TextView) rootView.findViewById(R.id.salidalocal_txtLocal_error);
        txtErrorSede_error = (TextView) rootView.findViewById(R.id.salidalocal_txtSede_error);
        txtRegistroDireccion = (TextView) rootView.findViewById(R.id.salidalocal_txtdireccion_error);

        cvRBungalow = (CardView) rootView.findViewById(R.id.salidalocal_cvRbungalow);
        lyNBungalow = (LinearLayout) rootView.findViewById(R.id.salidalocal_LYNbungalow);


        edtDni.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        return rootView;
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtDni.requestFocus();
        //EDITTEXT BUSCAR
        edtDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // edtDni.setText(" ");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(edtDni.getText().length()==8){
                    ocultarTeclado(edtDni);
                    String dni = edtDni.getText().toString();
                    if(!dni.equals("")){
                        if(!validarDNI(dni)) {
                            cvRegistro.setVisibility(View.GONE);
                            cvYaregistrado.setVisibility(View.GONE);
                            cvError.setVisibility(View.GONE);
                            cvNoregistrado.setVisibility(View.VISIBLE);
                            cvAviso.setVisibility(View.GONE);
                            edtDni.setText("");
                            edtDni.requestFocus();
                        }
                        else{
                            if(validarSEDE(dni))
                            { buscarDNI(dni);
                                edtDni.setText("");
                                edtDni.requestFocus();
                            }
                            else{cvError.setVisibility(View.VISIBLE);
                                cvNoregistrado.setVisibility(View.GONE);
                                cvRegistro.setVisibility(View.GONE);
                                cvYaregistrado.setVisibility(View.GONE);
                                cvAviso.setVisibility(View.GONE);
                                edtDni.setText("");
                                edtDni.requestFocus();
                            }

                        }
                    }
                    else {
                        Toast.makeText(context, "Ingrese DNI ", Toast.LENGTH_SHORT).show();
                        edtDni.setText("");
                        edtDni.requestFocus();}
                }

            }
        });
        //BOTON BUSCAR
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = edtDni.getText().toString();
                if(!dni.equals("")){
                    if(!validarDNI(dni)) {
                        cvRegistro.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.GONE);
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.VISIBLE);
                        cvAviso.setVisibility(View.GONE);
                        edtDni.setText("");
                        edtDni.requestFocus();
                    }
                    else{
                        if(validarSEDE(dni))
                        { buscarDNI(dni);
                            edtDni.setText("");
                            edtDni.requestFocus();
                        }
                        else{cvError.setVisibility(View.VISIBLE);
                            cvNoregistrado.setVisibility(View.GONE);
                            cvRegistro.setVisibility(View.GONE);
                            cvYaregistrado.setVisibility(View.GONE);
                            cvAviso.setVisibility(View.GONE);
                            edtDni.setText("");
                            edtDni.requestFocus();
                        }

                    }
                }
                else {
                    Toast.makeText(context, "Ingrese DNI ", Toast.LENGTH_SHORT).show();
                    edtDni.setText("");
                    edtDni.requestFocus();}
            }
        });
    }

    public boolean buscarDNI(String dni){
        boolean encontrado = false;

        try {
            Data data = new Data(context);
            data.open();
            AsistenteModelo1 asistenteModelo1 = data.getAsistencia2(dni);
            data.close();
            if(asistenteModelo1 != null){
                encontrado = true;
                data = new Data(context);
                data.open();
                AsistenteModelo1 asistentes = data.getAsistencia2(asistenteModelo1.getNumdoc());
                if(asistentes.getEstatus2()==1){
                    //YA REGISTRADO
                    cvError.setVisibility(View.GONE);
                    cvNoregistrado.setVisibility(View.GONE);
                    cvYaregistrado.setVisibility(View.VISIBLE);
                    cvRegistro.setVisibility(View.GONE);
                    cvAviso.setVisibility(View.GONE);
                    txtRegistroDni_yaregistrado.setText(asistenteModelo1.getNumdoc());
                    txtRegistroNombres_yaregistrado.setText(asistenteModelo1.getApepat());
                    txtRegistroFecha.setText(checkDigito(asistenteModelo1.getDia2())+"/"+checkDigito(asistenteModelo1.getMes2()+1)+"/"+(asistenteModelo1.getAnio2()+1900)+"  -  "+checkDigito(asistenteModelo1.getHora2())+":"+checkDigito(asistenteModelo1.getMinuto2()));

                }
                else{
                    //NUEVO REGISTRADO
                    cvError.setVisibility(View.GONE);
                    cvNoregistrado.setVisibility(View.GONE);
                    cvYaregistrado.setVisibility(View.GONE);
                    cvRegistro.setVisibility(View.VISIBLE);
                    cvAviso.setVisibility(View.GONE);
                    cvRBungalow.setVisibility(View.VISIBLE);
                    lyNBungalow.setVisibility(View.VISIBLE);
                    txtRegistroSede.setText(asistenteModelo1.getSede());
                    txtRegistroNombres.setText(asistenteModelo1.getApepat());
                    txtRegistroDni.setText(asistenteModelo1.getNumdoc());
                    txtRegistroLocal.setText(asistenteModelo1.getCargo());
                    txtRegistroCargo.setText(asistenteModelo1.getCargo());
                    txtRegistroAula.setText(asistenteModelo1.getAula());
                    txtRegistroNbungalow.setText(""+asistenteModelo1.getBungalow());
                    if(asistenteModelo1.getResponsable_bungalow()==1)
                    {txtRegistroRbungalow.setText("Responsable de Bungalow : SI ");}
                    else {txtRegistroRbungalow.setText("Responsable de Bungalow : NO  ");}
                    Calendar calendario = Calendar.getInstance();
                    int yy = calendario.get(Calendar.YEAR)-1900;
                    int mm = calendario.get(Calendar.MONTH);
                    int dd = calendario.get(Calendar.DAY_OF_MONTH);
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minuto = calendario.get(Calendar.MINUTE);
                    int segundo = calendario.get(Calendar.SECOND);

                    ContentValues registroactualizado = new ContentValues();
                    registroactualizado.put("dia2", dd);
                    registroactualizado.put("mes2", mm);
                    registroactualizado.put("anio2", yy);
                    registroactualizado.put("hora2", hora);
                    registroactualizado.put("minuto2", minuto);
                    registroactualizado.put("estatus2",1);
                    data.actualizarAsistencia2(dni, registroactualizado);
                }
                data.close();
            }
            else {
                //NO SE REGISTRO ENTRADA
                Data dato = new Data(context);
                dato.open();
                Nacional nacional = dato.getNacional(dni);
                data.close();
                cvError.setVisibility(View.GONE);
                cvNoregistrado.setVisibility(View.GONE);
                cvYaregistrado.setVisibility(View.GONE);
                cvRegistro.setVisibility(View.GONE);
                cvAviso.setVisibility(View.VISIBLE);
                txtErrorSede_error.setText(nacional.getSede());
                txtErrorLocal_error.setText(nacional.getSede());
                txtRegistroDireccion.setText(nacional.getDireccion_local());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encontrado;
    }

    public boolean validarDNI(String dni){
        boolean encontrado = false;

        try {
            Data data = new Data(context);
            data.open();
            Nacional nacional = data.getNacional(dni);
            data.close();
            if(nacional != null){
                encontrado = true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encontrado;
    }

    public boolean validarSEDE(String dni){
        boolean encontrado = false;

        try {
            Data data = new Data(context);
            data.open();
            Nacional nacional = data.getNacional(dni);
            data.close();
            if(nro_local.equals(String.valueOf(nacional.getNro_local()))){
                encontrado = true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encontrado;
    }


    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
