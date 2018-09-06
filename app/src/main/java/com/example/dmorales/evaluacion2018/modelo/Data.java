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
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA1);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA2);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA3);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA41);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA42);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA43);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA51);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA52);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA53);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA54);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA55);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA6);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA71);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_ASISTENCIA72);


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
                usuario.setNro_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_nro_local)));
                usuario.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_local_aplicacion)));
                usuario.setSede_region(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_sede_region)));
                usuario.setCod_nivel(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_cod_nivel)));
                usuario.setNom_nivel(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_nom_nivel)));
                usuario.setFase(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_fase)));
                usuario.setRol(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_local_rol)));
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
                nacional.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                nacional.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                nacional.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                nacional.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                nacional.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                nacional.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                nacional.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                nacional.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                nacional.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                nacional.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                nacional.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                nacional.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                nacional.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                nacional.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                nacional.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                nacional.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                nacional.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                nacional.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                nacional.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return nacional;
    }

    //VALIDACIONES DE DIAS
    public AsistenteModelo3 getValidacionDia41(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia41,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getValidacionDia42(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia42,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getValidacionDia51(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia51,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getValidacionDia52(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia52,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getValidacionDia53(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia53,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getValidacionDia54(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia54,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getValidacionDia71(){
        AsistenteModelo3  asistenteModelo3 = null;
        String[] whereArgs = new String[]{"1","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia71,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_DIA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    //REGISTRADO-SALIDA LOCAL
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
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrado;
    }


    //INSERCIONES A TABLAS//
    public void insertarFechaRegistro(Registrado registrado){
        ContentValues contentValues = registrado.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablafecharegistro,null,contentValues);
    }
    public void insertarAsistencia1(AsistenteModelo1 asistenteModelo1){
        ContentValues contentValues = asistenteModelo1.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia1,null,contentValues);
    }
    public void insertarAsistencia2(AsistenteModelo1 asistenteModelo1){
        ContentValues contentValues = asistenteModelo1.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia2,null,contentValues);
    }
    public void insertarAsistencia3(AsistenteModelo2 asistenteModelo2){
        ContentValues contentValues = asistenteModelo2.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia3,null,contentValues);
    }
    public void insertarAsistencia41(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia41,null,contentValues);
    }
    public void insertarAsistencia42(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia42,null,contentValues);
    }
    public void insertarAsistencia43(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia43,null,contentValues);
    }
    public void insertarAsistencia51(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia51,null,contentValues);
    }
    public void insertarAsistencia52(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia52,null,contentValues);
    }
    public void insertarAsistencia53(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia53,null,contentValues);
    }
    public void insertarAsistencia54(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia54,null,contentValues);
    }
    public void insertarAsistencia55(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia55,null,contentValues);
    }
    public void insertarAsistencia6(AsistenteModelo2 asistenteModelo2){
        ContentValues contentValues = asistenteModelo2.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia6,null,contentValues);
    }
    public void insertarAsistencia71(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia71,null,contentValues);
    }
    public void insertarAsistencia72(AsistenteModelo3 asistenteModelo3){
        ContentValues contentValues = asistenteModelo3.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablaasistencia72,null,contentValues);
    }

    //FIN --->INSERCIONES A TABLAS//

    //ACTUALIZACIONES A TABLAS
    public void actualizarFechaRegistro(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablafecharegistro,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }

    public void actualizarAsistencia1(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia1,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia2(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia2,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia3(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia3,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia41(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia41,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia42(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia42,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia43(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia43,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia51(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia51,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia52(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia52,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia53(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia53,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia54(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia54,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia55(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia55,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia6(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia6,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia71(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia71,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    public void actualizarAsistencia72(String codigo, ContentValues valores){
        String[] whereArgs = new String[]{codigo};
        sqLiteDatabase.update(SQLConstantes.tablaasistencia72,valores,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs);
    }
    //FIN-----> ACTUALIZACIONES A TABLAS


    //EXISTE DNI EN TABLA
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
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrado;
    }

    public AsistenteModelo1 getAsistencia1(String dni){
        AsistenteModelo1 asistenteModelo1 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia1,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO1,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo1 = new AsistenteModelo1();
                asistenteModelo1.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo1.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo1.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo1.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo1.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo1.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo1.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistenteModelo1.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo1.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo1.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo1.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistenteModelo1.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo1.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo1.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo1.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo1.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo1.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo1.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo1.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo1.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo1.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo1.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo1.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo1.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo1.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo1;
    }
    public AsistenteModelo1 getAsistencia2(String dni){
        AsistenteModelo1 asistenteModelo1 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia2,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO1,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo1 = new AsistenteModelo1();
                asistenteModelo1.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo1.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo1.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo1.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo1.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo1.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo1.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistenteModelo1.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo1.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo1.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo1.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistenteModelo1.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo1.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo1.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo1.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo1.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo1.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo1.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo1.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo1.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo1.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo1.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo1.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo1.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo1.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo1;
    }
    public AsistenteModelo2 getAsistencia3(String dni){
        AsistenteModelo2 asistenteModelo2 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia3,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO2,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo2 = new AsistenteModelo2();
                asistenteModelo2.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo2.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo2.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo2.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo2.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo2.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo2.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo2.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo2.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo2.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo2.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo2.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo2.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo2.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo2.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo2.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo2;
    }
    public AsistenteModelo3 getAsistencia41(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia41,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia42(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia42,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia43(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia43,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia51(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia51,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia52(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia52,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia53(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia53,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia54(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia54,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia55(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia55,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo2 getAsistencia6(String dni){
        AsistenteModelo2 asistenteModelo2 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia6,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO2,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo2 = new AsistenteModelo2();
                asistenteModelo2.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo2.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo2.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo2.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo2.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo2.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo2.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo2.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo2.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo2.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo2.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo2.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo2.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo2.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo2.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo2.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo2;
    }
    public AsistenteModelo3 getAsistencia71(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia71,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }
    public AsistenteModelo3 getAsistencia72(String dni){
        AsistenteModelo3 asistenteModelo3 = null;
        String[] whereArgs = new String[]{dni};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia72,
                    SQLConstantes.COLUMNAS_ASISTENCIATIPO3,SQLConstantes.WHERE_CLAUSE_CODIGO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                asistenteModelo3 = new AsistenteModelo3();
                asistenteModelo3.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistenteModelo3.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistenteModelo3.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistenteModelo3.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistenteModelo3.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistenteModelo3.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistenteModelo3.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistenteModelo3.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistenteModelo3.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistenteModelo3.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistenteModelo3.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistenteModelo3.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistenteModelo3.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistenteModelo3.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistenteModelo3.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistenteModelo3.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistenteModelo3.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistenteModelo3.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistenteModelo3.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistenteModelo3.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistenteModelo3.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistenteModelo3.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistenteModelo3.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistenteModelo3;
    }

    //CONTEO DE  TODOS LOS REGISTROS DE  TABLAS
    public ArrayList<Registrado> getAllRegistrados(String cod_local){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<AsistenteModelo1> getAllAsistentes1(String cod_local){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia1,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo1> getAllAsistentes2(String cod_local){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia2,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllAsistentes3(String cod_local){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia3,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes41(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia41,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes42(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia42,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes43(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia43,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes51(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia51,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes52(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia52,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes53(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia53,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes54(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia54,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes55(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia55,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllAsistentes6(String cod_local){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia6,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes71(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia71,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllAsistentes72(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia72,
                    null,SQLConstantes.WHERE_CLAUSE_COD_LOCAL,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }

    //OBTENER OBJETOS PARA SUBIR A FIREBASE
    //ESTATUS1 Y SUBIDO1
    public ArrayList<Registrado> getAllRegistradosTemporal(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<AsistenteModelo1> getAllEstado1Asistentes1(){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia1,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo1> getAllEstado1Asistentes2(){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia2,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllEstado1Asistentes3(){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia3,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes41(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia41,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes42(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia42,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes43(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia43,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes51(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia51,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes52(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia52,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes53(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia53,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes54(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia54,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes55(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia55,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllEstado1Asistentes6(){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia6,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes71(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia71,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado1Asistentes72(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia72,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }

    //ESTATUS1 Y SUBIDO1
    public ArrayList<Registrado> getAllRegistradosTemporal2(){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }
    public ArrayList<AsistenteModelo1> getAllEstado2Asistentes1(){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia1,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo1> getAllEstado2Asistentes2(){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia2,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllEstado2Asistentes3(){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia3,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes41(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia41,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes42(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia42,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes43(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia43,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes51(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia51,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes52(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia52,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes53(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia53,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes54(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia54,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes55(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia55,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllEstado2Asistentes6(){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia6,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes71(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia71,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllEstado2Asistentes72(){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{"0","1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia72,
                    null,SQLConstantes.WHERE_CLAUSE_SUBIDO2,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }


    //FIN--->OBTENER OBJETOS PARA SUBIR A FIREBASE


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
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    //MOSTRAR POR SEDE-NUBE
    public ArrayList<Registrado> getSedeRegistradosNube(String cod_local){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    public ArrayList<AsistenteModelo1> getAllNubeAsistentes1(String cod_local){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia1,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo1> getAllNubeAsistentes2(String cod_local){
        ArrayList<AsistenteModelo1> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia2,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo1 asistente = new AsistenteModelo1();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllNubeAsistentes3(String cod_local){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia3,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes41(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia41,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes42(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia42,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes43(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia43,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes51(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia51,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes52(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia52,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes53(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia53,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes54(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia54,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes55(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia55,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo2> getAllNubeAsistentes6(String cod_local){
        ArrayList<AsistenteModelo2> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia6,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo2 asistente = new AsistenteModelo2();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes71(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia71,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }
    public ArrayList<AsistenteModelo3> getAllNubeAsistentes72(String cod_local){
        ArrayList<AsistenteModelo3> asistentes = new ArrayList<>();
        String[] whereArgs = new String[]{cod_local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaasistencia72,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                AsistenteModelo3 asistente = new AsistenteModelo3();
                asistente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                asistente.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                asistente.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                asistente.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                asistente.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                asistente.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                asistente.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                asistente.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                asistente.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                asistente.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                asistente.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                asistente.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                asistente.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                asistente.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                asistente.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                asistente.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                asistente.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                asistente.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                asistente.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                asistente.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                asistente.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                asistente.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                asistente.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                asistente.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                asistentes.add(asistente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return asistentes;
    }


    //MOSTRAR POR LOCAL
    public ArrayList<Registrado> getLocalRegistradosNube(String local){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{local,"1"};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_SEDESUBIDO1,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    //MOSTRAR POR DIA
    public ArrayList<Registrado> getSedeRegistradosDia(String aula){
        ArrayList<Registrado> registrados = new ArrayList<>();
        String[] whereArgs = new String[]{aula};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafecharegistro,
                    null,SQLConstantes.WHERE_CLAUSE_AULA,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Registrado registrado = new Registrado();
                registrado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_id)));
                registrado.setSede(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede)));
                registrado.setNro_local(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nro_local)));
                registrado.setLocal_aplicacion(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_local_aplicacion)));
                registrado.setDireccion_local(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_direccion_local)));
                registrado.setCod_sede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_reg)));
                registrado.setCod_sede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_prov)));
                registrado.setCod_sede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cod_sede_distrital)));
                registrado.setSede_reg(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_reg)));
                registrado.setSede_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_prov)));
                registrado.setSede_distrital(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_sede_distrital)));
                registrado.setAula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_aula)));
                registrado.setBungalow(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_bungalow)));
                registrado.setApepat(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_apepat)));
                registrado.setNumdoc(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_numdoc)));
                registrado.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipo)));
                registrado.setTipocargo(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_tipocargo)));
                registrado.setCargo(cursor.getString(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_cargo)));
                registrado.setNivel(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_nivel)));
                registrado.setResponsable_bungalow(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_responsable_bungalow)));
                registrado.setEstatus1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus1)));
                registrado.setDia1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia1)));
                registrado.setMes1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes1)));
                registrado.setAnio1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio1)));
                registrado.setHora1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora1)));
                registrado.setMinuto1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto1)));
                registrado.setEstatus2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_estatus2)));
                registrado.setDia2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_dia2)));
                registrado.setMes2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_mes2)));
                registrado.setAnio2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_anio2)));
                registrado.setHora2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_hora2)));
                registrado.setMinuto2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_minuto2)));
                registrado.setSubido1(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido1)));
                registrado.setSubido2(cursor.getInt(cursor.getColumnIndex(SQLConstantes.fecha_de_registro_subido2)));
                registrados.add(registrado);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return registrados;
    }

    //RESETEAR TABLAS
    public void deleteAllElementosFromTabla(String nombreTabla){
        sqLiteDatabase.execSQL("delete from "+ nombreTabla);
    }
    public void deleteAllconsulta(String dni){
        sqLiteDatabase.execSQL("SELECT * FROM asistencia WHERE numdoc='"+dni+"' and _id=(SELECT max(_id)FROM asistencia WHERE numdoc='00094378')");
    }


}
