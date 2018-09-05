package com.example.dmorales.evaluacion2018.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;

import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment1;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment3;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment1;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment3;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment1;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment3;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment1;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;

import java.io.IOException;

public class MainActivity3 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //ENVIA TEXTO DE NIVEL
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo21);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo22);
        txtHeaderTitulo1.setText("Asistencia "+fase);
        txtHeaderTitulo2.setText(nombrenivel);

        IngresoLocalFragment3 ingresolocalFragment3 = new IngresoLocalFragment3(nro_local,MainActivity3.this);
        fragmentTransaction.replace(R.id.fragment_layout, ingresolocalFragment3);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int id = item.getItemId();

        if (id == R.id.menu_ingresolocal2) {
            IngresoLocalFragment3 ingresoLocalFragment3 = new IngresoLocalFragment3(nro_local,MainActivity3.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresoLocalFragment3);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_listado2) {
            ListadoFragment3 listadoFragment3 = new ListadoFragment3(usuario,nro_local,MainActivity3.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment3);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_nube2) {
            NubeFragment3 nubeFragment3 = new NubeFragment3(nro_local,MainActivity3.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment3);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_reset_bd2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Está seguro que desea borrar los datos?")
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
                                Data data = new Data(MainActivity3.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia3);
                                data.close();
                                ListadoFragment3 listadoFragment3 = new ListadoFragment3(usuario,nro_local,MainActivity3.this);
                                FragmentManager fragmentManage = getSupportFragmentManager();
                                FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                fragmentTransact.replace(R.id.fragment_layout, listadoFragment3);
                                fragmentTransact.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.menu_cerrar_sesion2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                                    Intent intent = new Intent(MainActivity3.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
