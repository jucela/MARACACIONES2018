package com.example.dmorales.evaluacion2018.modelo;

import android.content.ContentValues;

public class AsistenteModelo2 {
    private String _id;
    private String sede;
    private int nro_local;
    private String local_aplicacion;
    private String direccion_local;
    private String aula;
    private String apepat;
    private String numdoc;
    private String cargo;
    private int nivel;
    private int estatus1;
    private int dia1;
    private int mes1;
    private int anio1;
    private int hora1;
    private int minuto1;
    private int subido1;

    public AsistenteModelo2(String _id, String sede, int nro_local, String local_aplicacion, String direccion_local, String aula, String apepat, String numdoc, String cargo, int nivel, int estatus1, int dia1, int mes1, int anio1, int hora1, int minuto1, int subido1) {
        this._id = _id;
        this.sede = sede;
        this.nro_local = nro_local;
        this.local_aplicacion = local_aplicacion;
        this.direccion_local = direccion_local;
        this.aula = aula;
        this.apepat = apepat;
        this.numdoc = numdoc;
        this.cargo = cargo;
        this.nivel = nivel;
        this.estatus1 = estatus1;
        this.dia1 = dia1;
        this.mes1 = mes1;
        this.anio1 = anio1;
        this.hora1 = hora1;
        this.minuto1 = minuto1;
        this.subido1 = subido1;
    }

    public AsistenteModelo2(){
        this._id="";
        this.sede="";
        this.nro_local=0;
        this.local_aplicacion="";
        this.direccion_local ="";
        this.aula="";
        this.apepat="";
        this.numdoc="";
        this.cargo="";
        this.nivel=0;
        this.estatus1=0;
        this.dia1=0;
        this.mes1=0;
        this.anio1=0;
        this.hora1=0;
        this.minuto1=0;
        this.subido1 = 0;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getNro_local() {
        return nro_local;
    }

    public void setNro_local(int nro_local) {
        this.nro_local = nro_local;
    }

    public String getLocal_aplicacion() {
        return local_aplicacion;
    }

    public void setLocal_aplicacion(String local_aplicacion) {
        this.local_aplicacion = local_aplicacion;
    }

    public String getDireccion_local() {
        return direccion_local;
    }

    public void setDireccion_local(String direccion_local) {
        this.direccion_local = direccion_local;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getEstatus1() {
        return estatus1;
    }

    public void setEstatus1(int estatus1) {
        this.estatus1 = estatus1;
    }

    public int getDia1() {
        return dia1;
    }

    public void setDia1(int dia1) {
        this.dia1 = dia1;
    }

    public int getMes1() {
        return mes1;
    }

    public void setMes1(int mes1) {
        this.mes1 = mes1;
    }

    public int getAnio1() {
        return anio1;
    }

    public void setAnio1(int anio1) {
        this.anio1 = anio1;
    }

    public int getHora1() {
        return hora1;
    }

    public void setHora1(int hora1) {
        this.hora1 = hora1;
    }

    public int getMinuto1() {
        return minuto1;
    }

    public void setMinuto1(int minuto1) {
        this.minuto1 = minuto1;
    }


    public int getSubido1() {
        return subido1;
    }

    public void setSubido1(int subido1) {
        this.subido1 = subido1;
    }



    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.fecha_de_registro_id,_id);
        contentValues.put(SQLConstantes.fecha_de_registro_sede,sede);
        contentValues.put(SQLConstantes.fecha_de_registro_nro_local,nro_local);
        contentValues.put(SQLConstantes.fecha_de_registro_local_aplicacion,local_aplicacion);
        contentValues.put(SQLConstantes.fecha_de_registro_direccion_local,direccion_local);
        contentValues.put(SQLConstantes.fecha_de_registro_aula,aula);
        contentValues.put(SQLConstantes.fecha_de_registro_apepat,apepat);
        contentValues.put(SQLConstantes.fecha_de_registro_numdoc,numdoc);
        contentValues.put(SQLConstantes.fecha_de_registro_cargo,cargo);
        contentValues.put(SQLConstantes.fecha_de_registro_nivel,nivel);
        contentValues.put(SQLConstantes.fecha_de_registro_estatus1,estatus1);
        contentValues.put(SQLConstantes.fecha_de_registro_dia1,dia1);
        contentValues.put(SQLConstantes.fecha_de_registro_mes1,mes1);
        contentValues.put(SQLConstantes.fecha_de_registro_anio1,anio1);
        contentValues.put(SQLConstantes.fecha_de_registro_hora1,hora1);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto1,minuto1);
        contentValues.put(SQLConstantes.fecha_de_registro_subido1,subido1);
        return contentValues;
    }
}
