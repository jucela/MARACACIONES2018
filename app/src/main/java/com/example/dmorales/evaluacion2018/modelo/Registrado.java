package com.example.dmorales.evaluacion2018.modelo;

import android.content.ContentValues;

public class Registrado {
    private String _id;
    private String codigo;
    private String nombres;
    private String sede;
    private String aula;
    private String dia;
    private String mes;
    private String anio;
    private String hora;
    private String minuto;
    private String sdia;
    private String smes;
    private String sanio;
    private String shora;
    private String sminuto;
    private int subido;
    private String estado1;
    private String estado2;
    private String local;


//    public Registrado(String _id, String codigo, String nombres, String sede, String aula, String dia, String mes, String anio, String hora, String minuto, int subido, String estado1, String estado2, String local) {
//        this._id = _id;
//        this.codigo = codigo;
//        this.nombres = nombres;
//        this.sede = sede;
//        this.aula = aula;
//        this.dia = dia;
//        this.mes = mes;
//        this.anio = anio;
//        this.hora = hora;
//        this.minuto = minuto;
//        this.subido = subido;
//        this.estado1 = estado1;
//        this.estado2 = estado2;
//        this.local = local;
//    }


    public Registrado(String _id, String codigo, String nombres, String sede, String aula, String dia, String mes, String anio, String hora, String minuto, String sdia, String smes, String sanio, String shora, String sminuto, int subido, String estado1, String estado2, String local) {
        this._id = _id;
        this.codigo = codigo;
        this.nombres = nombres;
        this.sede = sede;
        this.aula = aula;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = hora;
        this.minuto = minuto;
        this.sdia = sdia;
        this.smes = smes;
        this.sanio = sanio;
        this.shora = shora;
        this.sminuto = sminuto;
        this.subido = subido;
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.local = local;
    }

    public Registrado() {
        this._id = "";
        this.codigo = "";
        this.nombres = "";
        this.sede = "";
        this.aula = "";
        this.dia = "";
        this.mes = "";
        this.anio = "";
        this.hora = "";
        this.minuto = "";
        this.sdia = "";
        this.smes = "";
        this.sanio = "";
        this.shora = "";
        this.sminuto = "";
        this.subido = 0;
        this.estado1 = "1";
        this.estado2 = "0";
        this.local = "2";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public int getSubido() {
        return subido;
    }

    public String getSdia() {
        return sdia;
    }

    public void setSdia(String sdia) {
        this.sdia = sdia;
    }

    public String getSmes() {
        return smes;
    }

    public void setSmes(String smes) {
        this.smes = smes;
    }

    public String getSanio() {
        return sanio;
    }

    public void setSanio(String sanio) {
        this.sanio = sanio;
    }

    public String getShora() {
        return shora;
    }

    public void setShora(String shora) {
        this.shora = shora;
    }

    public String getSminuto() {
        return sminuto;
    }

    public void setSminuto(String sminuto) {
        this.sminuto = sminuto;
    }

    public void setSubido(int subido) {
        this.subido = subido;
    }

    public String getEstado1() {
        return estado1;
    }

    public void setEstado1(String estado1) {
        this.estado1 = estado1;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.fecha_de_registro_id,_id);
        contentValues.put(SQLConstantes.fecha_de_registro_codigo,codigo);
        contentValues.put(SQLConstantes.fecha_de_registro_nombres,nombres);
        contentValues.put(SQLConstantes.fecha_de_registro_sede,sede);
        contentValues.put(SQLConstantes.fecha_de_registro_aula,aula);
        contentValues.put(SQLConstantes.fecha_de_registro_dia,dia);
        contentValues.put(SQLConstantes.fecha_de_registro_mes,mes);
        contentValues.put(SQLConstantes.fecha_de_registro_anio,anio);
        contentValues.put(SQLConstantes.fecha_de_registro_hora,hora);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto,minuto);
        contentValues.put(SQLConstantes.fecha_de_registro_subido,subido);
        contentValues.put(SQLConstantes.fecha_de_registro_sdia,sdia);
        contentValues.put(SQLConstantes.fecha_de_registro_smes,smes);
        contentValues.put(SQLConstantes.fecha_de_registro_sanio,sanio);
        contentValues.put(SQLConstantes.fecha_de_registro_shora,shora);
        contentValues.put(SQLConstantes.fecha_de_registro_sminuto,sminuto);
        contentValues.put(SQLConstantes.fecha_de_registro_subido,subido);
        contentValues.put(SQLConstantes.fecha_de_registro_estado1,estado1);
        contentValues.put(SQLConstantes.fecha_de_registro_estado2,estado2);
        contentValues.put(SQLConstantes.fecha_de_registro_local,local);
        return contentValues;
    }
}
