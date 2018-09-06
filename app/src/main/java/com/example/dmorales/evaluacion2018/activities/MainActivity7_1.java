package com.example.dmorales.evaluacion2018.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.adapters.ExpandListAdapter;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment71;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment72;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment72_1;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment71;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment72;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment71;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment72;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment71;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment72;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;
import com.example.dmorales.evaluacion2018.util.TipoFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity7_1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    String rol;
    private ArrayList<String> listDataHeader;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer3);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        rol = getIntent().getExtras().getString("rol");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer3);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                ocultarTeclado(drawerView);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        enableExpandableList();
        setFragment(TipoFragment.ASISTENCIA_INGRESO1);

        //  NOMBRE NIVEL
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view333);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo71);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo72);
        if(rol.equals("1"))
        {txtHeaderTitulo1.setText("Usuario: Administrador");}
        else
        {txtHeaderTitulo1.setText("Usuario: Operador");}
        txtHeaderTitulo2.setText(nombrenivel);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer3);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void setFragment(int tipoFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (tipoFragment){
            case TipoFragment.ASISTENCIA_INGRESO1:
                IngresoLocalFragment71 ingresolocaltroFragment71 = new IngresoLocalFragment71(nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment71);
                break;
            case TipoFragment.ASISTENCIA_SALIDA1 :
                SalidaLocalFragment71 salidaLocalFragment71 = new SalidaLocalFragment71(nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment71);
                break;
            case TipoFragment.ASISTENCIA_LISTADO1:
                ListadoFragment71 listadoFragment71 = new ListadoFragment71(usuario,nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment71);
                break;
            case TipoFragment.ASISTENCIA_NUBE1:
                NubeFragment71 nubeFragment71 = new NubeFragment71(nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment71);
                break;
            case TipoFragment.ASISTENCIA_INGRESO2:
                IngresoLocalFragment72_1 ingresolocaltroFragment72_1 = new IngresoLocalFragment72_1(nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment72_1);
                break;
            case TipoFragment.ASISTENCIA_SALIDA2 :
                SalidaLocalFragment72 salidaLocalFragment72 = new SalidaLocalFragment72(nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment72);
                break;
            case TipoFragment.ASISTENCIA_LISTADO2:
                ListadoFragment72 listadoFragment72 = new ListadoFragment72(usuario,nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment72);
                break;
            case TipoFragment.ASISTENCIA_NUBE2:
                NubeFragment72 nubeFragment72 = new NubeFragment72(nro_local,MainActivity7_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment72);
                break;

        }
        fragmentTransaction.commit();
    }

    private void enableExpandableList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.expandable_principal3);

        prepareListData(listDataHeader, listDataChild);
        listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                setFragment(TipoFragment.ASISTENCIA_INGRESO1);
                                break;
                            case 1:
                                setFragment(TipoFragment.ASISTENCIA_SALIDA1);
                                break;
                            case 2:
                                setFragment(TipoFragment.ASISTENCIA_LISTADO1);
                                break;
                            case 3:
                                setFragment(TipoFragment.ASISTENCIA_NUBE1);
                                break;
                            case 4:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7_1.this);
                                builder.setMessage("¿Está seguro que desea borrar los datos del DIA 1?")
                                        .setTitle("Aviso")
                                        .setCancelable(false)
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                try {
                                                    Data data = new Data(MainActivity7_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia71);
                                                    data.close();
                                                    ListadoFragment71 listadoFragment71 = new ListadoFragment71(usuario,nro_local, MainActivity7_1.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment71);
                                                    fragmentTransact.commit();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                setFragment(TipoFragment.ASISTENCIA_INGRESO2);
                                break;
                            case 1:
                                setFragment(TipoFragment.ASISTENCIA_SALIDA2);
                                break;
                            case 2:
                                setFragment(TipoFragment.ASISTENCIA_LISTADO2);
                                break;
                            case 3:
                                setFragment(TipoFragment.ASISTENCIA_NUBE2);
                                break;
                            case 4:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7_1.this);
                                builder.setMessage("¿Está seguro que desea borrar los datos del DIA 2?")
                                        .setTitle("Aviso")
                                        .setCancelable(false)
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                try {
                                                    Data data = new Data(MainActivity7_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia72);
                                                    data.close();
                                                    ListadoFragment72 listadoFragment72 = new ListadoFragment72(usuario,nro_local, MainActivity7_1.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment72);
                                                    fragmentTransact.commit();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                        }
                        break;

                    case 2:
                        switch (childPosition) {
                            case 0:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7_1.this);
                                builder.setMessage("¿Está seguro que desea cerrar sesión en la aplicación?")
                                        .setTitle("Aviso")
                                        .setCancelable(false)
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent intent = new Intent(MainActivity7_1.this,LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();



                        }
                        break;

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer3);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void prepareListData(List<String> listDataHeader, Map<String, List<String>> listDataChild) {
        listDataHeader.add("Día 1");
        listDataHeader.add("Día 2");
        listDataHeader.add("Otros");

        // Adding child data
        List<String> dia1 = new ArrayList<String>();
        dia1.add("Ingreso Local (Día 1)");
        dia1.add("Reingreso Local (Día 1)");
        dia1.add("Subir Registros (Día 1)");
        dia1.add("Reporte de Marcación (Día 1)");
        dia1.add("Reset BD (Día 1)");

        List<String> dia2 = new ArrayList<String>();
        dia2.add("Ingreso Local (Día 2)");
        dia2.add("Reingreso Local (Día 2)");
        dia2.add("Subir Registros (Día 2)");
        dia2.add("Reporte de Marcación (Día 2)");
        dia2.add("Reset BD (Día 2)");


        List<String> otros = new ArrayList<String>();
        otros.add("Cerrar Sesión");


        listDataChild.put(listDataHeader.get(0), dia1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), dia2);
        listDataChild.put(listDataHeader.get(2),otros);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
