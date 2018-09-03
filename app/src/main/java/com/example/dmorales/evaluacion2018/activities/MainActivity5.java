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
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment51;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment52;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment53;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment54;
import com.example.dmorales.evaluacion2018.fragments.IngresoLocalFragment55;
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

import java.io.IOException;

public class MainActivity5 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String usuario;
    String sede;
    String nro_local;
    String nombrenivel;
    String fase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main45);

        nro_local = getIntent().getExtras().getString("nro_local");
        sede = getIntent().getExtras().getString("sede");
        usuario = getIntent().getExtras().getString("usuario");
        nombrenivel = getIntent().getExtras().getString("nombrenivel");
        fase = getIntent().getExtras().getString("fase");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout45);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view45);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //ENVIA TEXTO DE NIVEL
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderTitulo1 = (TextView) headerView.findViewById(R.id.titulo551);
        TextView txtHeaderTitulo2 = (TextView) headerView.findViewById(R.id.titulo552);
        txtHeaderTitulo1.setText("Asistencia "+fase);
        txtHeaderTitulo2.setText(nombrenivel);

        IngresoLocalFragment51 ingresoLocalFragment51 = new IngresoLocalFragment51(nro_local,MainActivity5.this);
        fragmentTransaction.replace(R.id.fragment_layout, ingresoLocalFragment51);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout45);
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

        if (id == R.id.menu_ingresolocal51) {
            IngresoLocalFragment51 ingresolocaltroFragment51 = new IngresoLocalFragment51(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment51);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_ingresolocal52) {
            IngresoLocalFragment52 ingresolocaltroFragment52 = new IngresoLocalFragment52(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment52);
            fragmentTransaction.commit();

        } else  if (id == R.id.menu_ingresolocal53) {
            IngresoLocalFragment53 ingresolocaltroFragment53 = new IngresoLocalFragment53(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment53);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_ingresolocal54) {
            IngresoLocalFragment54 ingresolocaltroFragment54 = new IngresoLocalFragment54(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment54);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_ingresolocal55) {
            IngresoLocalFragment55 ingresolocaltroFragment55 = new IngresoLocalFragment55(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, ingresolocaltroFragment55);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal51) {
            SalidaLocalFragment51 salidaLocalFragment51 = new SalidaLocalFragment51(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment51);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal52) {
            SalidaLocalFragment52 salidaLocalFragment52 = new SalidaLocalFragment52(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment52);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal53) {
            SalidaLocalFragment53 salidaLocalFragment53 = new SalidaLocalFragment53(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment53);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal54) {
            SalidaLocalFragment54 salidaLocalFragment54 = new SalidaLocalFragment54(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment54);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_salidalocal55) {
            SalidaLocalFragment55 salidaLocalFragment55 = new SalidaLocalFragment55(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout,salidaLocalFragment55);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_listado51) {
            ListadoFragment51 listadoFragment51 = new ListadoFragment51(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment51);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado52) {
            ListadoFragment52 listadoFragment52 = new ListadoFragment52(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment52);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado53) {
            ListadoFragment53 listadoFragment53 = new ListadoFragment53(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment53);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado54) {
            ListadoFragment54 listadoFragment54 = new ListadoFragment54(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment54);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_listado55) {
            ListadoFragment55 listadoFragment55 = new ListadoFragment55(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, listadoFragment55);
            fragmentTransaction.commit();
        } else if (id == R.id.menu_nube51) {
            NubeFragment51 nubeFragment51 = new NubeFragment51(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment51);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube52) {
            NubeFragment52 nubeFragment52 = new NubeFragment52(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment52);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube53) {
            NubeFragment53 nubeFragment53 = new NubeFragment53(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment53);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube54) {
            NubeFragment54 nubeFragment54 = new NubeFragment54(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment54);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_nube55) {
            NubeFragment55 nubeFragment55 = new NubeFragment55(nro_local,MainActivity5.this);
            fragmentTransaction.replace(R.id.fragment_layout, nubeFragment55);
            fragmentTransaction.commit();

        } else if (id == R.id.menu_reset_bd51) {
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
                                Data data = new Data(MainActivity5.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia51);
                                data.close();
                                ListadoFragment51 listadoFragment51 = new ListadoFragment51(nro_local,MainActivity5.this);
                                FragmentManager fragmentManage = getSupportFragmentManager();
                                FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                fragmentTransact.replace(R.id.fragment_layout, listadoFragment51);
                                fragmentTransact.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.menu_reset_bd52) {
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
                                Data data = new Data(MainActivity5.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia52);
                                data.close();
                                ListadoFragment52 listadoFragment52 = new ListadoFragment52(nro_local,MainActivity5.this);
                                FragmentManager fragmentManage = getSupportFragmentManager();
                                FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                fragmentTransact.replace(R.id.fragment_layout, listadoFragment52);
                                fragmentTransact.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.menu_reset_bd53) {
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
                                Data data = new Data(MainActivity5.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia53);
                                data.close();
                                ListadoFragment53 listadoFragment53 = new ListadoFragment53(nro_local,MainActivity5.this);
                                FragmentManager fragmentManage = getSupportFragmentManager();
                                FragmentTransaction fragmentTransact = fragmentManage.beginTransaction();
                                fragmentTransact.replace(R.id.fragment_layout, listadoFragment53);
                                fragmentTransact.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.menu_reset_bd54) {
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
                                Data data = new Data(MainActivity5.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia54);
                                data.close();
                                ListadoFragment54 listadoFragment54 = new ListadoFragment54(nro_local,MainActivity5.this);
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


        } else if (id == R.id.menu_reset_bd55) {
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
                                Data data = new Data(MainActivity5.this);
                                data.open();
                                data.deleteAllElementosFromTabla(SQLConstantes.tablaasistencia55);
                                data.close();
                                ListadoFragment55 listadoFragment55 = new ListadoFragment55(nro_local,MainActivity5.this);
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


        } else if (id == R.id.menu_cerrar_sesion45) {
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
                                    Intent intent = new Intent(MainActivity5.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout45);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
