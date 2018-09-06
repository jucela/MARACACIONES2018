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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment41;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment42;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment43;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment41;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment42;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment43;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment41;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment42;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment43;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment41;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment42;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment43;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;
import com.example.dmorales.evaluacion2018.util.TipoFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_drawer1);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        rol = getIntent().getExtras().getString("rol");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer1);
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
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view11);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo431);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo432);
        if(rol.equals("1"))
        {txtHeaderTitulo1.setText("Usuario: Administrador");}
        else
        {txtHeaderTitulo1.setText("Usuario: Operador");}
        txtHeaderTitulo2.setText(nombrenivel);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer1);
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
                IngresoLocalFragment41 ingresolocaltroFragment41 = new IngresoLocalFragment41(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment41);
                break;
            case TipoFragment.ASISTENCIA_SALIDA1 :
                SalidaLocalFragment41 salidaLocalFragment41 = new SalidaLocalFragment41(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment41);
                break;
            case TipoFragment.ASISTENCIA_LISTADO1:
                ListadoFragment41 listadoFragment41 = new ListadoFragment41(usuario,nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment41);
                break;
            case TipoFragment.ASISTENCIA_NUBE1:
                NubeFragment41 nubeFragment41 = new NubeFragment41(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment41);
                break;
            case TipoFragment.ASISTENCIA_INGRESO2:
                IngresoLocalFragment42 ingresolocaltroFragment42 = new IngresoLocalFragment42(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment42);
                break;
            case TipoFragment.ASISTENCIA_SALIDA2 :
                SalidaLocalFragment42 salidaLocalFragment42 = new SalidaLocalFragment42(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment42);
                break;
            case TipoFragment.ASISTENCIA_LISTADO2:
                ListadoFragment42 listadoFragment42 = new ListadoFragment42(usuario,nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment42);
                break;
            case TipoFragment.ASISTENCIA_NUBE2:
                NubeFragment42 nubeFragment42 = new NubeFragment42(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment42);
                break;
            case TipoFragment.ASISTENCIA_INGRESO3:
                IngresoLocalFragment43 ingresolocaltroFragment43 = new IngresoLocalFragment43(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment43);
                break;
            case TipoFragment.ASISTENCIA_SALIDA3 :
                SalidaLocalFragment43 salidaLocalFragment43 = new SalidaLocalFragment43(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment43);
                break;
            case TipoFragment.ASISTENCIA_LISTADO3:
                ListadoFragment43 listadoFragment43 = new ListadoFragment43(usuario,nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment43);
                break;
            case TipoFragment.ASISTENCIA_NUBE3:
                NubeFragment43 nubeFragment43 = new NubeFragment43(nro_local,MainActivity4.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment43);
                break;

        }
        fragmentTransaction.commit();
    }

    private void enableExpandableList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.expandable_principal1);

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
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
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
                                                    Data data = new Data(MainActivity4.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia41);
                                                    data.close();
                                                    ListadoFragment41 listadoFragment41 = new ListadoFragment41(usuario,nro_local, MainActivity4.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment41);
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
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
                                                    Data data = new Data(MainActivity4.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia42);
                                                    data.close();
                                                    ListadoFragment42 listadoFragment42 = new ListadoFragment42(usuario,nro_local, MainActivity4.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment42);
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
                                setFragment(TipoFragment.ASISTENCIA_INGRESO3);
                                break;
                            case 1:
                                setFragment(TipoFragment.ASISTENCIA_SALIDA3);
                                break;
                            case 2:
                                setFragment(TipoFragment.ASISTENCIA_LISTADO3);
                                break;
                            case 3:
                                setFragment(TipoFragment.ASISTENCIA_NUBE3);
                                break;
                            case 4:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                                builder.setMessage("¿Está seguro que desea borrar los datos del DIA 3?")
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
                                                    Data data = new Data(MainActivity4.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia43);
                                                    data.close();
                                                    ListadoFragment43 listadoFragment43 = new ListadoFragment43(usuario,nro_local, MainActivity4.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment43);
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

                    case 3:
                        switch (childPosition) {
                            case 0:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
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
                                                Intent intent = new Intent(MainActivity4.this,LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();



                        }
                        break;

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer1);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void prepareListData(List<String> listDataHeader, Map<String, List<String>> listDataChild) {
        listDataHeader.add("Día 1");
        listDataHeader.add("Día 2");
        listDataHeader.add("Día 3");
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

        List<String> dia3 = new ArrayList<String>();
        dia3.add("Ingreso Local (Día 3)");
        dia3.add("Reingreso Local (Día 3)");
        dia3.add("Subir Registros (Día 3)");
        dia3.add("Reporte de Marcación (Día 3)");
        dia3.add("Reset BD (Día 3)");

        List<String> otros = new ArrayList<String>();
        otros.add("Cerrar Sesión");


        listDataChild.put(listDataHeader.get(0), dia1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), dia2);
        listDataChild.put(listDataHeader.get(2), dia3);
        listDataChild.put(listDataHeader.get(3),otros);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
