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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment2;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment41;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment42;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment43;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment2;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment41;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment42;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment43;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment2;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment41;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment42;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment43;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment2;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment41;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment42;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment43;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;

import java.io.IOException;

public class MainActivity4 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout33);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view33);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //ENVIA TEXTO DE NIVEL
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo431);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo432);
        txtHeaderTitulo1.setText("Asistencia "+fase);
        txtHeaderTitulo2.setText(nombrenivel);

        IngresoLocalFragment41 ingresoLocalFragment41 = new IngresoLocalFragment41(nro_local,MainActivity4.this);
        fragmentTransaction.replace(R.id.fragment_layout, ingresoLocalFragment41);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout33);
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

        if (id == R.id.menu_ingresolocal41) {
            IngresoLocalFragment41 ingresolocaltroFragment41 = new IngresoLocalFragment41(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment41);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_ingresolocal42) {
            IngresoLocalFragment42 ingresolocaltroFragment42 = new IngresoLocalFragment42(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment42);
            fragmentTransaction.commit();

        } else  if (id == R.id.menu_ingresolocal43) {
            IngresoLocalFragment43 ingresolocaltroFragment43 = new IngresoLocalFragment43(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment43);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal41) {
            SalidaLocalFragment41 salidaLocalFragment41 = new SalidaLocalFragment41(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment41);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal42) {
            SalidaLocalFragment42 salidaLocalFragment42 = new SalidaLocalFragment42(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment42);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal43) {
            SalidaLocalFragment43 salidaLocalFragment43 = new SalidaLocalFragment43(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment43);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_listado41) {
            ListadoFragment41 listadoFragment41 = new ListadoFragment41(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment41);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado42) {
            ListadoFragment42 listadoFragment42 = new ListadoFragment42(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment42);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado43) {
            ListadoFragment43 listadoFragment43 = new ListadoFragment43(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment43);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_nube41) {
            NubeFragment41 nubeFragment41 = new NubeFragment41(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment41);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube42) {
            NubeFragment42 nubeFragment42 = new NubeFragment42(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment42);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube43) {
            NubeFragment43 nubeFragment43 = new NubeFragment43(nro_local,MainActivity4.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment43);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_reset_bd41) {
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
                                Data data = new Data(MainActivity4.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia41);
                                data.close();
                                ListadoFragment41 listadoFragment41 = new ListadoFragment41(nro_local,MainActivity4.this);
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


        } else if (id == R.id.menu_reset_bd42) {
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
                                Data data = new Data(MainActivity4.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia42);
                                data.close();
                                ListadoFragment42 listadoFragment42 = new ListadoFragment42(nro_local,MainActivity4.this);
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


        } else if (id == R.id.menu_reset_bd43) {
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
                                Data data = new Data(MainActivity4.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia43);
                                data.close();
                                ListadoFragment43 listadoFragment43 = new ListadoFragment43(nro_local,MainActivity4.this);
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


        } else if (id == R.id.menu_cerrar_sesion33) {
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
                                    Intent intent = new Intent(MainActivity4.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout33);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
