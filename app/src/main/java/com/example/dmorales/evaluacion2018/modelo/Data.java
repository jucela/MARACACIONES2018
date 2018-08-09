package com.example.dmorales.evaluacion2018.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Data {
    Context contexto;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public Data(Context contexto) throws IOException {
        this.contexto = contexto;
        sqLiteOpenHelper = new DataBaseHelper(contexto);
        createDataBase();
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(!dbExist){
            sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
            sqLiteDatabase.close();
            try{
                copyDataBase();
                sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FECHA_REGISTRO);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FECHA_REGISTRO_TEMPORAL);
                sqLiteDatabase.close();
            }catch (IOException e){
                throw new Error("Error: copiando base de datos");
            }
        }

    }

    public void copyDataBase() throws IOException{
        InputStream myInput = contexto.getAssets().open(SQLConstantes.DB_NAME);
        String outFileName = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) != -1){
            if (length > 0){
                myOutput.write(buffer,0,length);
            }
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();

    }



    public void open() throws SQLException {
        String myPath = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close(){
        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
        }
    }

    public boolean checkDataBase(){
        try{
            String myPath = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
            File dbFile = new File(SQLConstantes.DB_PATH + SQLConstantes.DB_NAME);
            return dbFile.exists();
        }
        if (sqLiteDatabase != null) sqLiteDatabase.close();

        return sqLiteDatabase != null ? true : false;
    }

    public UsuarioLocal getUsuarioLocal(String clave){
        UsuarioLocal usuario = null;
        String[] whereArgs = new String[]{clave};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablausuariolocal,
                    SQLConstantes.COLUMNAS_USUARIO_LOCAL,SQLConstantes.WHERE_CLAUSE_CLAVE,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                usuario = new UsuarioLocal();
                usuario.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_usuario)));
                usuario.setClave(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_clave)));
                usuario.setNombrelocal(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_nombrelocal)));
                usuario.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_sede)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return usuario;
    }

    public Nacional getNacional(String dni){
        Nacional nacional = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablanacional,
                    SQLConstantes.COLUMNAS_NACIONAL,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                nacional = new Nacional();
                nacional.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_codigo)));
                nacional.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_sede)));
                nacional.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_id_local)));
                nacional.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_nom_local)));
                nacional.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_aula)));
                nacional.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_nombres)));
                nacional.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.nacional_cargo)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return nacional;
    }
    //REGISTRADO
    public Registrado getRegistrado(String dni){
        Registrado registrado = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    SQLConstantes.COLUMNAS_FECHA_REGISTRO_TEMPORAL ,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                registrado = new Registrado();
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrado;
    }


    //NORMAL//

    public void insertarFechaRegistro(Registrado registrado){
        ContentValues contentValues = registrado.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablafecharegistro,null,contentValues);
    }

    public void actualizarFechaRegistro(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablafecharegistro,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }

    public Registrado getFechaRegistro(String dni){
        Registrado registrado = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    SQLConstantes.COLUMNAS_FECHA_REGISTRO,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                registrado = new Registrado();
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrado;
    }

    public ArrayList<Registrado> getAllRegistrados(String sede){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{sede};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SEDE,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<Registrado> getAllRegistradosTemporal(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"0"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<Registrado> getAllRegistradosTemporal2(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"0"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<Registrado> getAllRegistradosTemporal3(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"0"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO3,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<Registrado> getAllRegistradosTemporal4(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"0"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO4,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }
    //MOSTRAR TODOS
    public ArrayList<Registrado> getAllRegistradosNube(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    //MOSTRAR POR SEDE
    public ArrayList<Registrado> getSedeRegistradosNube(String sede){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{sede,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setCodigo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_codigo)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setId_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id_local)));
                registrado.setNom_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nom_local)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nombres)));
                registrado.setDia1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setDia2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setDia3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia3)));
                registrado.setMes3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes3)));
                registrado.setAnio3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio3)));
                registrado.setHora3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora3)));
                registrado.setMinuto3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto3)));
                registrado.setDia4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia4)));
                registrado.setMes4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes4)));
                registrado.setAnio4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio4)));
                registrado.setHora4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora4)));
                registrado.setMinuto4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto4)));
                registrado.setEstado1(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado1)));
                registrado.setEstado2(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado2)));
                registrado.setEstado3(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado3)));
                registrado.setEstado4(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estado4)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrado.setSubido3(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido3)));
                registrado.setSubido4(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido4)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }


    public void deleteAllElementosFromTabla(String nombreTabla){
        sqLiteDatabase.execSQL("delete from "+ nombreTabla);
    }

}
