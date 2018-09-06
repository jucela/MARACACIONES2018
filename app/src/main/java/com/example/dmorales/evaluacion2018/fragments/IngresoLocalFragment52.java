package com.example.dmorales.evaluacion2018.fragments;


import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmorales.evaluacion2018.NumericKeyBoardTransformationMethod;
import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.modelo.AsistenteModelo3;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.Nacional;

import java.io.IOException;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngresoLocalFragment52 extends Fragment {

    ImageView btnBuscar;
    EditText edtDni;

    CardView cvNoregistrado;
    CardView cvYaregistrado;
    CardView cvRegistro;
    CardView cvError;
    CardView cvAviso;

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



    public IngresoLocalFragment52() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public IngresoLocalFragment52(String nro_local, Context context) {
        this.nro_local = nro_local;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingresolocal, container, false);
        btnBuscar = (ImageView) rootView.findViewById(R.id.ingresolocal_btnBuscar);
        edtDni = (EditText) rootView.findViewById(R.id.ingresolocal_edtDni);

        cvError = (CardView) rootView.findViewById(R.id.ingresolocal_cvError);
        cvNoregistrado = (CardView) rootView.findViewById(R.id.ingresolocal_cvNoRegistrado);
        cvRegistro = (CardView) rootView.findViewById(R.id.ingresolocal_cvRegistro);
        cvYaregistrado = (CardView) rootView.findViewById(R.id.ingresolocal_cvYaRegistrado);
        cvAviso = (CardView) rootView.findViewById(R.id.ingresolocal_cvAviso);


        txtRegistroCargo = (TextView) rootView.findViewById(R.id.ingresolocal_txtCargo);
        txtRegistroDni = (TextView) rootView.findViewById(R.id.ingresolocal_txtDni);
        txtRegistroNombres = (TextView) rootView.findViewById(R.id.ingresolocal_txtNombres);
        txtRegistroSede = (TextView) rootView.findViewById(R.id.ingresolocal_txtSede);
        txtRegistroLocal = (TextView) rootView.findViewById(R.id.ingresolocal_txtLocal);
        txtRegistroAula = (TextView) rootView.findViewById(R.id.ingresolocal_txtAula);
        txtRegistroNbungalow = (TextView) rootView.findViewById(R.id.ingresolocal_txtNbungalow);
        txtRegistroRbungalow = (TextView) rootView.findViewById(R.id.ingresolocal_txtRbungalow);

        txtRegistroDni_yaregistrado = (TextView) rootView.findViewById(R.id.ingresolocal_txtDni_yaregistrado);
        txtRegistroNombres_yaregistrado = (TextView) rootView.findViewById(R.id.ingresolocal_txtNombres_yaregitstrado);
        txtRegistroFecha = (TextView) rootView.findViewById(R.id.ingresolocal_txtfecha);


        txtErrorLocal_error = (TextView) rootView.findViewById(R.id.ingresolocal_txtLocal_error);
        txtErrorSede_error = (TextView) rootView.findViewById(R.id.ingresolocal_txtSede_error);
        txtRegistroDireccion = (TextView) rootView.findViewById(R.id.ingresolocal_txtdireccion_error);


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
                        if (validarDIA()) {
                        if(!buscarDNI(dni)) {
                            cvRegistro.setVisibility(View.GONE);
                            cvYaregistrado.setVisibility(View.GONE);
                            cvError.setVisibility(View.GONE);
                            cvNoregistrado.setVisibility(View.VISIBLE);
                            edtDni.setText("");
                            edtDni.requestFocus();
                        }
                        else{edtDni.setText("");
                            edtDni.requestFocus();}
                        }
                        else{edtDni.setText("");
                            edtDni.requestFocus();
                            cvError.setVisibility(View.GONE);
                            cvNoregistrado.setVisibility(View.GONE);
                            cvYaregistrado.setVisibility(View.GONE);
                            cvRegistro.setVisibility(View.GONE);
                            cvAviso.setVisibility(View.VISIBLE);
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
                    if(!buscarDNI(dni)) {
                        //NO EXISTE EN PADRON
                        cvRegistro.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.GONE);
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.VISIBLE);
                        edtDni.setText("");
                        edtDni.requestFocus();
                    }
                    else{edtDni.setText("");
                        edtDni.requestFocus();}
                }
                else {
                Toast.makeText(context, "Ingrese DNI ", Toast.LENGTH_SHORT).show();edtDni.setText("");
                    edtDni.requestFocus();}
            }
        });
    }

    public boolean buscarDNI(String dni){
        boolean encontrado = false;

        try {
            Data data = new Data(context);
            data.open();
            Nacional nacional = data.getNacional(dni);
            data.close();
            if(nacional != null){
                encontrado = true;
                if(nro_local.equals(String.valueOf(nacional.getNro_local()))){
                    data = new Data(context);
                    data.open();
                    AsistenteModelo3 asistenteModelo3 = data.getAsistencia52(nacional.getNumdoc());
                    if(asistenteModelo3 != null){
                        //YA REGISTRADO
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.VISIBLE);
                        cvRegistro.setVisibility(View.GONE);
                        txtRegistroDni_yaregistrado.setText(asistenteModelo3.getNumdoc());
                        txtRegistroNombres_yaregistrado.setText(asistenteModelo3.getApepat());
                        txtRegistroFecha.setText(checkDigito(asistenteModelo3.getDia1())+"/"+checkDigito(asistenteModelo3.getMes1()+1)+"/"+(asistenteModelo3.getAnio1()+1900)+"  -  "+checkDigito(asistenteModelo3.getHora1())+":"+checkDigito(asistenteModelo3.getMinuto1()));
                    }else{
                        //NUEVO REGISTRADO
                        cvError.setVisibility(View.GONE);
                        cvNoregistrado.setVisibility(View.GONE);
                        cvYaregistrado.setVisibility(View.GONE);
                        cvRegistro.setVisibility(View.VISIBLE);
                        txtRegistroSede.setText(nacional.getSede());
                        txtRegistroNombres.setText(nacional.getApepat());
                        txtRegistroDni.setText(nacional.getNumdoc());
                        txtRegistroLocal.setText(nacional.getCargo());
                        txtRegistroCargo.setText(nacional.getCargo());
                        txtRegistroAula.setText(nacional.getAula());
                        Calendar calendario = Calendar.getInstance();
                        int yy = calendario.get(Calendar.YEAR)-1900;
                        int mm = calendario.get(Calendar.MONTH);
                        int dd = calendario.get(Calendar.DAY_OF_MONTH);
                        int hora = calendario.get(Calendar.HOUR_OF_DAY);
                        int minuto = calendario.get(Calendar.MINUTE);
                        int segundo = calendario.get(Calendar.SECOND);
                        int estatus1 = 1;
                        int estatus2 = 0;
                        //2018-08-17 15:23:41.000
                        AsistenteModelo3 asistente = new AsistenteModelo3(
                                dni,
                                nacional.getSede(),
                                nacional.getNro_local(),
                                nacional.getLocal_aplicacion(),
                                nacional.getDireccion_local(),
                                nacional.getAula(),
                                nacional.getApepat(),
                                nacional.getNumdoc(),
                                nacional.getCargo(),
                                nacional.getNivel(),
                                estatus1,
                                dd,
                                mm,
                                yy,
                                hora,
                                minuto,
                                estatus2,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0);
                        data.insertarAsistencia52(asistente);

                    }
                    data.close();
                }else{
                    //PERTENECE A OTRA SEDE
                    cvError.setVisibility(View.VISIBLE);
                    cvNoregistrado.setVisibility(View.GONE);
                    cvRegistro.setVisibility(View.GONE);
                    cvYaregistrado.setVisibility(View.GONE);
                    txtErrorSede_error.setText(nacional.getSede());
                    txtErrorLocal_error.setText(nacional.getSede());
                    txtRegistroDireccion.setText(nacional.getDireccion_local());
                }
            }
            else
                { Toast.makeText(context, "EL DNI NO EXISTE EN EL MARCO", Toast.LENGTH_SHORT).show(); }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encontrado;
    }

    public boolean validarDIA(){
        boolean encontrado = false;

        try {
            Data data = new Data(context);
            data.open();
            AsistenteModelo3 modelo3 = data.getValidacionDia51();
            data.close();
            if(modelo3 !=null){
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
