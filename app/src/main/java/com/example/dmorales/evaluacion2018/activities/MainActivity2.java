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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment2;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment2;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment2;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment2;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //ENVIA TEXTO DE NIVEL
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo11);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo12);
        txtHeaderTitulo1.setText("Asistencia "+fase);
        txtHeaderTitulo2.setText(nombrenivel);

        IngresoLocalFragment2 ingresoLocalFragment2 = new IngresoLocalFragment2(nro_local,MainActivity2.this);
        fragmentTransaction.replace(R.id.fragment_layout, ingresoLocalFragment2);
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

        if (id == R.id.menu_ingresolocal) {
            IngresoLocalFragment2 ingresolocaltroFragment2 = new IngresoLocalFragment2(nro_local,MainActivity2.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment2);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal) {
            SalidaLocalFragment2 salidaLocalFragment2 = new SalidaLocalFragment2(nro_local,MainActivity2.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment2);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_listado) {
            ListadoFragment2 listadoFragment2 = new ListadoFragment2(nro_local,MainActivity2.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment2);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_nube) {
            NubeFragment2 nubeFragment2 = new NubeFragment2(nro_local,MainActivity2.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment2);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_reset_bd) {
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
                                Data data = new Data(MainActivity2.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia2);
                                data.close();
                                ListadoFragment2 listadoFragment2 = new ListadoFragment2(nro_local,MainActivity2.this);
                                FragmentManager fragmentManage = getSupportFragmentManager();
                                FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                fragmentTransact.replace(R.id.fragment_layout, listadoFragment2);
                                fragmentTransact.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.menu_cerrar_sesion) {
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
                                    Intent intent = new Intent(MainActivity2.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
