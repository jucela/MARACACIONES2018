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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment1;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment;
import com.example.dmorales.evaluacion2018.fragments.ListadoFragment1;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment;
import com.example.dmorales.evaluacion2018.fragments.NubeFragment1;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment;
import com.example.dmorales.evaluacion2018.fragments.SalidaLocalFragment1;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.SQLConstantes;

import java.io.IOException;

public class MainActivity1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        rol = getIntent().getExtras().getString("rol");
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
        if(rol.equals("1"))
        {txtHeaderTitulo1.setText("Usuario: Administrador");}
        else
        {txtHeaderTitulo1.setText("Usuario: Operador");}
        txtHeaderTitulo2.setText(nombrenivel);

        IngresoLocalFragment1 ingresolocalFragment1 = new IngresoLocalFragment1(nro_local,MainActivity1.this);
        fragmentTransaction.replace(R.id.fragment_layout, ingresolocalFragment1);
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
            IngresoLocalFragment1 ingresolocalFragment1 = new IngresoLocalFragment1(nro_local,MainActivity1.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocalFragment1);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal) {
            SalidaLocalFragment1 salidalocalFragment1 = new SalidaLocalFragment1(nro_local,MainActivity1.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidalocalFragment1);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_listado) {
            ListadoFragment1 listadoFragment1 = new ListadoFragment1(usuario,nro_local,MainActivity1.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment1);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_nube) {
            NubeFragment1 nubeFragment1 = new NubeFragment1(nro_local,MainActivity1.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment1);
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
                                Data data = new Data(MainActivity1.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia1);
                                data.close();
                                ListadoFragment1 listadoFragment1 = new ListadoFragment1(usuario,nro_local,MainActivity1.this);
                                FragmentManager fragmentManage = getSupportFragmentManager();
                                FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                fragmentTransact.replace(R.id.fragment_layout, listadoFragment1);
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
                                    Intent intent = new Intent(MainActivity1.this,LoginActivity.class);
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
