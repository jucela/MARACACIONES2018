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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment51;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment52;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment52_1;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment53;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment53_1;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment54;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment54_1;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment55;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment55_1;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment41;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment51;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment52;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment53;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment54;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment55;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment51;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment52;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment53;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment54;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment55;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment51;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment52;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment53;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment54;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment55;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;
import com.example.dmorales.evaluacion2018.util.TipoFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity5_1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_drawer2);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer2);
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
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view22);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo551);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo552);
        txtHeaderTitulo1.setText("Asistencia "+fase);
        txtHeaderTitulo2.setText(nombrenivel);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer2);
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
                IngresoLocalFragment51 ingresolocaltroFragment51 = new IngresoLocalFragment51(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment51);
                break;
            case TipoFragment.ASISTENCIA_SALIDA1 :
                SalidaLocalFragment51 salidaLocalFragment51 = new SalidaLocalFragment51(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment51);
                break;
            case TipoFragment.ASISTENCIA_LISTADO1:
                ListadoFragment51 listadoFragment51 = new ListadoFragment51(usuario,nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment51);
                break;
            case TipoFragment.ASISTENCIA_NUBE1:
                NubeFragment51 nubeFragment51 = new NubeFragment51(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment51);
                break;
            case TipoFragment.ASISTENCIA_INGRESO2:
                IngresoLocalFragment52_1 ingresolocaltroFragment52_1 = new IngresoLocalFragment52_1(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment52_1);
                break;
            case TipoFragment.ASISTENCIA_SALIDA2 :
                SalidaLocalFragment52 salidaLocalFragment52 = new SalidaLocalFragment52(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment52);
                break;
            case TipoFragment.ASISTENCIA_LISTADO2:
                ListadoFragment52 listadoFragment52 = new ListadoFragment52(usuario,nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment52);
                break;
            case TipoFragment.ASISTENCIA_NUBE2:
                NubeFragment52 nubeFragment52 = new NubeFragment52(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment52);
                break;
            case TipoFragment.ASISTENCIA_INGRESO3:
                IngresoLocalFragment53_1 ingresolocaltroFragment53_1 = new IngresoLocalFragment53_1(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment53_1);
                break;
            case TipoFragment.ASISTENCIA_SALIDA3 :
                SalidaLocalFragment53 salidaLocalFragment53 = new SalidaLocalFragment53(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment53);
                break;
            case TipoFragment.ASISTENCIA_LISTADO3:
                ListadoFragment53 listadoFragment53 = new ListadoFragment53(usuario,nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment53);
                break;
            case TipoFragment.ASISTENCIA_NUBE3:
                NubeFragment53 nubeFragment53 = new NubeFragment53(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment53);
                break;
            case TipoFragment.ASISTENCIA_INGRESO4:
                IngresoLocalFragment54_1 ingresolocaltroFragment54_1 = new IngresoLocalFragment54_1(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment54_1);
                break;
            case TipoFragment.ASISTENCIA_SALIDA4 :
                SalidaLocalFragment54 salidaLocalFragment54 = new SalidaLocalFragment54(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment54);
                break;
            case TipoFragment.ASISTENCIA_LISTADO4:
                ListadoFragment54 listadoFragment54 = new ListadoFragment54(usuario,nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment54);
                break;
            case TipoFragment.ASISTENCIA_NUBE4:
                NubeFragment54 nubeFragment54 = new NubeFragment54(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment54);
                break;
            case TipoFragment.ASISTENCIA_INGRESO5:
                IngresoLocalFragment55_1 ingresolocaltroFragment55_1 = new IngresoLocalFragment55_1(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment55_1);
                break;
            case TipoFragment.ASISTENCIA_SALIDA5 :
                SalidaLocalFragment55 salidaLocalFragment55 = new SalidaLocalFragment55(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment55);
                break;
            case TipoFragment.ASISTENCIA_LISTADO5:
                ListadoFragment55 listadoFragment55 = new ListadoFragment55(usuario,nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, listadoFragment55);
                break;
            case TipoFragment.ASISTENCIA_NUBE5:
                NubeFragment55 nubeFragment55 = new NubeFragment55(nro_local,MainActivity5_1.this);
                fragmentTransaction.replace(R.id.fragment_layout, nubeFragment55);
                break;

        }
        fragmentTransaction.commit();
    }

    private void enableExpandableList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.expandable_principal2);

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
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5_1.this);
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
                                                    Data data = new Data(MainActivity5_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia51);
                                                    data.close();
                                                    ListadoFragment41 listadoFragment41 = new ListadoFragment41(usuario,nro_local, MainActivity5_1.this);
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5_1.this);
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
                                                    Data data = new Data(MainActivity5_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia52);
                                                    data.close();
                                                    ListadoFragment41 listadoFragment41 = new ListadoFragment41(usuario,nro_local, MainActivity5_1.this);
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5_1.this);
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
                                                    Data data = new Data(MainActivity5_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia53);
                                                    data.close();
                                                    ListadoFragment41 listadoFragment41 = new ListadoFragment41(usuario,nro_local, MainActivity5_1.this);
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

                    case 3:
                        switch (childPosition) {
                            case 0:
                                setFragment(TipoFragment.ASISTENCIA_INGRESO4);
                                break;
                            case 1:
                                setFragment(TipoFragment.ASISTENCIA_SALIDA4);
                                break;
                            case 2:
                                setFragment(TipoFragment.ASISTENCIA_LISTADO4);
                                break;
                            case 3:
                                setFragment(TipoFragment.ASISTENCIA_NUBE4);
                                break;
                            case 4:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5_1.this);
                                builder.setMessage("¿Está seguro que desea borrar los datos del DIA 4?")
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
                                                    Data data = new Data(MainActivity5_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia54);
                                                    data.close();
                                                    ListadoFragment54 listadoFragment54 = new ListadoFragment54(usuario,nro_local, MainActivity5_1.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment54);
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
                    case 4:
                        switch (childPosition) {
                            case 0:
                                setFragment(TipoFragment.ASISTENCIA_INGRESO5);
                                break;
                            case 1:
                                setFragment(TipoFragment.ASISTENCIA_SALIDA5);
                                break;
                            case 2:
                                setFragment(TipoFragment.ASISTENCIA_LISTADO5);
                                break;
                            case 3:
                                setFragment(TipoFragment.ASISTENCIA_NUBE5);
                                break;
                            case 4:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5_1.this);
                                builder.setMessage("¿Está seguro que desea borrar los datos del DIA 5?")
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
                                                    Data data = new Data(MainActivity5_1.this);
                                                    data.open();
                                                    data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia55);
                                                    data.close();
                                                    ListadoFragment55 listadoFragment55 = new ListadoFragment55(usuario,nro_local, MainActivity5_1.this);
                                                    FragmentManager fragmentManage = getSupportFragmentManager();
                                                    FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                                    fragmentTransact.replace(R.id.fragment_layout, listadoFragment55);
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
                    case 5:
                        switch (childPosition) {
                            case 0:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5_1.this);
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
                                                Intent intent = new Intent(MainActivity5_1.this,LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();



                        }
                        break;

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_drawer2);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void prepareListData(List<String> listDataHeader, Map<String, List<String>> listDataChild) {
        listDataHeader.add("Día 1");
        listDataHeader.add("Día 2");
        listDataHeader.add("Día 3");
        listDataHeader.add("Día 4");
        listDataHeader.add("Día 5");
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

        List<String> dia4 = new ArrayList<String>();
        dia4.add("Ingreso Local (Día 4)");
        dia4.add("Reingreso Local (Día 4)");
        dia4.add("Subir Registros (Día 4)");
        dia4.add("Reporte de Marcación (Día 4)");
        dia4.add("Reset BD (Día 4)");

        List<String> dia5 = new ArrayList<String>();
        dia5.add("Ingreso Local (Día 5)");
        dia5.add("Reingreso Local (Día 5)");
        dia5.add("Subir Registros (Día 5)");
        dia5.add("Reporte de Marcación (Día 5)");
        dia5.add("Reset BD (Día 5)");

        List<String> otros = new ArrayList<String>();
        otros.add("Cerrar Sesión");


        listDataChild.put(listDataHeader.get(0), dia1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), dia2);
        listDataChild.put(listDataHeader.get(2), dia3);
        listDataChild.put(listDataHeader.get(3), dia4);
        listDataChild.put(listDataHeader.get(4), dia5);
        listDataChild.put(listDataHeader.get(5),otros);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
