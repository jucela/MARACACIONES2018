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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment41;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment42;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment43;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment71;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment72;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment41;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment42;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment43;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment71;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment72;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment41;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment42;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment43;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment71;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment72;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment41;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment42;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment43;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment71;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment72;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;

import java.io.IOException;

public class MainActivity7 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main52);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout52);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view52);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //ENVIA TEXTO DE NIVEL
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo71);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo72);
        txtHeaderTitulo1.setText("Asistencia "+fase);
        txtHeaderTitulo2.setText(nombrenivel);

        IngresoLocalFragment71 ingresoLocalFragment71 = new IngresoLocalFragment71(nro_local,MainActivity7.this);
        fragmentTransaction.replace(R.id.fragment_layout, ingresoLocalFragment71);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout52);
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

        if (id == R.id.menu_ingresolocal71) {
            IngresoLocalFragment71 ingresolocaltroFragment71 = new IngresoLocalFragment71(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment71);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_ingresolocal72) {
            IngresoLocalFragment72 ingresolocaltroFragment72 = new IngresoLocalFragment72(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment72);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal71) {
            SalidaLocalFragment71 salidaLocalFragment71 = new SalidaLocalFragment71(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment71);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal72) {
            SalidaLocalFragment72 salidaLocalFragment72 = new SalidaLocalFragment72(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment72);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_listado71) {
            ListadoFragment71 listadoFragment71 = new ListadoFragment71(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment71);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado72) {
            ListadoFragment72 listadoFragment72 = new ListadoFragment72(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment72);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_nube71) {
            NubeFragment71 nubeFragment71 = new NubeFragment71(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment71);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube72) {
            NubeFragment72 nubeFragment72 = new NubeFragment72(nro_local,MainActivity7.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment72);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_reset_bd71) {
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
                                Data data = new Data(MainActivity7.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia71);
                                data.close();
                                ListadoFragment71 listadoFragment71 = new ListadoFragment71(nro_local,MainActivity7.this);
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


        } else if (id == R.id.menu_reset_bd72) {
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
                                Data data = new Data(MainActivity7.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia72);
                                data.close();
                                ListadoFragment72 listadoFragment72 = new ListadoFragment72(nro_local,MainActivity7.this);
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


        } else if (id == R.id.menu_cerrar_sesion52) {
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
                                    Intent intent = new Intent(MainActivity7.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout52);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
