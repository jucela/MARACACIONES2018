package com.example.dmorales.evaluacion2018.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmorales.evaluacion2018.R;
import com.example.dmorales.evaluacion2018.modelo.Data;
import com.example.dmorales.evaluacion2018.modelo.UsuarioLocal;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtClave;
    Button btnIngresar;
    TextView btncargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtClave = (TextInputEditText) findViewById(R.id.login_edtClave);
        btnIngresar = (Button) findViewById(R.id.login_btnIngresar);
        btncargar =(TextView) findViewById(R.id.txt_cargarmarco);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar(edtClave.getText().toString());
            }
        });

        btncargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,CargarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void ingresar(String clave){
        try{
            Data data = new Data(LoginActivity.this);
            data.open();
            UsuarioLocal usuarioLocal = data.getUsuarioLocal(clave);
            data.close();

                  if (usuarioLocal != null){
                      if(usuarioLocal.getCod_nivel().equals("I"))
                      {  Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
                          intent.putExtra("nro_local", usuarioLocal.getNro_local());
                          intent.putExtra("sede", usuarioLocal.getSede_region());
                          intent.putExtra("usuario", usuarioLocal.getUsuario());
                          intent.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                          intent.putExtra("fase", usuarioLocal.getFase());
                          intent.putExtra("rol", usuarioLocal.getRol());
                          startActivity(intent);  }
                      else if(usuarioLocal.getCod_nivel().equals("II"))
                           { Intent intent2 = new Intent(LoginActivity.this, MainActivity2.class);
                               intent2.putExtra("nro_local", usuarioLocal.getNro_local());
                               intent2.putExtra("sede", usuarioLocal.getSede_region());
                               intent2.putExtra("usuario", usuarioLocal.getUsuario());
                               intent2.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                               intent2.putExtra("fase", usuarioLocal.getFase());
                               intent2.putExtra("rol", usuarioLocal.getRol());
                               startActivity(intent2);   }
                           else if(usuarioLocal.getCod_nivel().equals("III"))
                                {Intent intent3 = new Intent(LoginActivity.this, MainActivity3.class);
                                    intent3.putExtra("nro_local", usuarioLocal.getNro_local());
                                    intent3.putExtra("sede", usuarioLocal.getSede_region());
                                    intent3.putExtra("usuario", usuarioLocal.getUsuario());
                                    intent3.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                    intent3.putExtra("fase", usuarioLocal.getFase());
                                    intent3.putExtra("rol", usuarioLocal.getRol());
                                    startActivity(intent3);    }
                                else if(usuarioLocal.getCod_nivel().equals("IV") && usuarioLocal.getRol().equals("2"))
                                     {Intent intent4 = new Intent(LoginActivity.this, MainActivity4.class);
                                      intent4.putExtra("nro_local", usuarioLocal.getNro_local());
                                      intent4.putExtra("sede", usuarioLocal.getSede_region());
                                      intent4.putExtra("usuario", usuarioLocal.getUsuario());
                                      intent4.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                      intent4.putExtra("fase", usuarioLocal.getFase());
                                      intent4.putExtra("rol", usuarioLocal.getRol());
                                      startActivity(intent4);    }
                                else if(usuarioLocal.getCod_nivel().equals("IV") && usuarioLocal.getRol().equals("1"))
                                      {Intent intent4_1 = new Intent(LoginActivity.this, MainActivity4_1.class);
                                       intent4_1.putExtra("nro_local", usuarioLocal.getNro_local());
                                       intent4_1.putExtra("sede", usuarioLocal.getSede_region());
                                       intent4_1.putExtra("usuario", usuarioLocal.getUsuario());
                                       intent4_1.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                       intent4_1.putExtra("fase", usuarioLocal.getFase());
                                       intent4_1.putExtra("rol", usuarioLocal.getRol());
                                       startActivity(intent4_1);    }
                               else if(usuarioLocal.getCod_nivel().equals("V") && usuarioLocal.getRol().equals("2"))
                                    {Intent intent5 = new Intent(LoginActivity.this, MainActivity5.class);
                                     intent5.putExtra("nro_local", usuarioLocal.getNro_local());
                                     intent5.putExtra("sede", usuarioLocal.getSede_region());
                                     intent5.putExtra("usuario", usuarioLocal.getUsuario());
                                     intent5.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                     intent5.putExtra("fase", usuarioLocal.getFase());
                                     intent5.putExtra("rol", usuarioLocal.getRol());
                                     startActivity(intent5); }
                                 else if(usuarioLocal.getCod_nivel().equals("V") && usuarioLocal.getRol().equals("1"))
                                 {Intent intent5_1 = new Intent(LoginActivity.this, MainActivity5_1.class);
                                  intent5_1.putExtra("nro_local", usuarioLocal.getNro_local());
                                  intent5_1.putExtra("sede", usuarioLocal.getSede_region());
                                  intent5_1.putExtra("usuario", usuarioLocal.getUsuario());
                                  intent5_1.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                  intent5_1.putExtra("fase", usuarioLocal.getFase());
                                  intent5_1.putExtra("rol", usuarioLocal.getRol());
                                  startActivity(intent5_1); }
                               else if(usuarioLocal.getCod_nivel().equals("VI"))
                                    {Intent intent6 = new Intent(LoginActivity.this, MainActivity6.class);
                                     intent6.putExtra("nro_local", usuarioLocal.getNro_local());
                                     intent6.putExtra("sede", usuarioLocal.getSede_region());
                                     intent6.putExtra("usuario", usuarioLocal.getUsuario());
                                     intent6.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                     intent6.putExtra("fase", usuarioLocal.getFase());
                                     intent6.putExtra("rol", usuarioLocal.getRol());
                                     startActivity(intent6);    }
                              else if(usuarioLocal.getCod_nivel().equals("VII") && usuarioLocal.getRol().equals("2"))
                                   {Intent intent7 = new Intent(LoginActivity.this, MainActivity7.class);
                                    intent7.putExtra("nro_local", usuarioLocal.getNro_local());
                                    intent7.putExtra("sede", usuarioLocal.getSede_region());
                                    intent7.putExtra("usuario", usuarioLocal.getUsuario());
                                    intent7.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                    intent7.putExtra("fase", usuarioLocal.getFase());
                                    intent7.putExtra("rol", usuarioLocal.getRol());
                                    startActivity(intent7);}
                              else if(usuarioLocal.getCod_nivel().equals("VII") && usuarioLocal.getRol().equals("1"))
                                   {Intent intent7_1 = new Intent(LoginActivity.this, MainActivity7_1.class);
                                    intent7_1.putExtra("nro_local", usuarioLocal.getNro_local());
                                    intent7_1.putExtra("sede", usuarioLocal.getSede_region());
                                    intent7_1.putExtra("usuario", usuarioLocal.getUsuario());
                                    intent7_1.putExtra("nombrenivel", usuarioLocal.getNom_nivel());
                                    intent7_1.putExtra("fase", usuarioLocal.getFase());
                                    intent7_1.putExtra("rol", usuarioLocal.getRol());
                                    startActivity(intent7_1);}

                  }else{
                      Toast.makeText(this, "CLAVE NO REGISTRADA", Toast.LENGTH_SHORT).show();
                  }


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
