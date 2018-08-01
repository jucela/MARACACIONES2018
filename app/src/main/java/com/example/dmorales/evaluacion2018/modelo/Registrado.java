package com.example.dmorales.evaluacion2018.modelo;

import android.content.ContentValues;

public class Registrado {
    private String _id;
    private String codigo;
    private String sede;
    private String id_local;
    private String nom_local;
    private String aula;
    private String nombres;
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
    private String estado1;
    private String estado2;
    private int subido;


    public Registrado(String _id, String codigo, String sede, String id_local, String nom_local, String aula, String nombres, String dia, String mes, String anio, String hora, String minuto, String sdia, String smes, String sanio, String shora, String sminuto, String estado1, String estado2, int subido) {
        this._id = _id;
        this.codigo = codigo;
        this.sede = sede;
        this.id_local = id_local;
        this.nom_local = nom_local;
        this.aula = aula;
        this.nombres = nombres;
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
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.subido = subido;
    }

    public Registrado() {
        this._id = "";
        this.codigo = "";
        this.sede = "";
        this.id_local = "";
        this.nom_local = "";
        this.aula = "";
        this.nombres = "";
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
        this.estado1 = "";
        this.estado2 = "";
        this.subido = 0;

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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getId_local() {
        return id_local;
    }

    public void setId_local(String id_local) {
        this.id_local = id_local;
    }

    public String getNom_local() {
        return nom_local;
    }

    public void setNom_local(String nom_local) {
        this.nom_local = nom_local;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public int getSubido() {
        return subido;
    }

    public void setSubido(int subido) {
        this.subido = subido;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.fecha_de_registro_id,_id);
        contentValues.put(SQLConstantes.fecha_de_registro_codigo,codigo);
        contentValues.put(SQLConstantes.fecha_de_registro_nombres,nombres);
        contentValues.put(SQLConstantes.fecha_de_registro_sede,sede);
        contentValues.put(SQLConstantes.fecha_de_registro_id_local,id_local);
        contentValues.put(SQLConstantes.fecha_de_registro_nom_local,nom_local);
        contentValues.put(SQLConstantes.fecha_de_registro_aula,aula);
        contentValues.put(SQLConstantes.fecha_de_registro_dia,dia);
        contentValues.put(SQLConstantes.fecha_de_registro_mes,mes);
        contentValues.put(SQLConstantes.fecha_de_registro_anio,anio);
        contentValues.put(SQLConstantes.fecha_de_registro_hora,hora);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto,minuto);
        contentValues.put(SQLConstantes.fecha_de_registro_sdia,sdia);
        contentValues.put(SQLConstantes.fecha_de_registro_smes,smes);
        contentValues.put(SQLConstantes.fecha_de_registro_sanio,sanio);
        contentValues.put(SQLConstantes.fecha_de_registro_shora,shora);
        contentValues.put(SQLConstantes.fecha_de_registro_sminuto,sminuto);
        contentValues.put(SQLConstantes.fecha_de_registro_subido,subido);
        contentValues.put(SQLConstantes.fecha_de_registro_estado1,estado1);
        contentValues.put(SQLConstantes.fecha_de_registro_estado2,estado2);
        contentValues.put(SQLConstantes.fecha_de_registro_subido,subido);

        return contentValues;
    }
}
