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
    private String dia1;
    private String mes1;
    private String anio1;
    private String hora1;
    private String minuto1;
    private String dia2;
    private String mes2;
    private String anio2;
    private String hora2;
    private String minuto2;
    private String estado1;
    private String estado2;
    private int subido1;
    private int subido2;


    public Registrado(String _id, String codigo, String sede, String id_local, String nom_local, String aula, String nombres, String dia1, String mes1, String anio1, String hora1, String minuto1, String dia2, String mes2, String anio2, String hora2, String minuto2, String estado1, String estado2, int subido1, int subido2) {
        this._id = _id;
        this.codigo = codigo;
        this.sede = sede;
        this.id_local = id_local;
        this.nom_local = nom_local;
        this.aula = aula;
        this.nombres = nombres;
        this.dia1 = dia1;
        this.mes1 = mes1;
        this.anio1 = anio1;
        this.hora1 = hora1;
        this.minuto1 = minuto1;
        this.dia2 = dia2;
        this.mes2 = mes2;
        this.anio2 = anio2;
        this.hora2 = hora2;
        this.minuto2 = minuto2;
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.subido1 = subido1;
        this.subido2 = subido2;
    }

    public Registrado() {
        this._id = "";
        this.codigo = "";
        this.sede = "";
        this.id_local = "";
        this.nom_local = "";
        this.aula = "";
        this.nombres = "";
        this.dia1 = "";
        this.mes1 = "";
        this.anio1 = "";
        this.hora1 = "";
        this.minuto1 = "";
        this.dia2 = "";
        this.mes2 = "";
        this.anio2 = "";
        this.hora2 = "";
        this.minuto2 = "";
        this.estado1 = "";
        this.estado2 = "";
        this.subido1 = 0;
        this.subido2 = 0;

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

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDia1() {
        return dia1;
    }

    public void setDia1(String dia1) {
        this.dia1 = dia1;
    }

    public String getMes1() {
        return mes1;
    }

    public void setMes1(String mes1) {
        this.mes1 = mes1;
    }

    public String getAnio1() {
        return anio1;
    }

    public void setAnio1(String anio1) {
        this.anio1 = anio1;
    }

    public String getHora1() {
        return hora1;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

    public String getMinuto1() {
        return minuto1;
    }

    public void setMinuto1(String minuto1) {
        this.minuto1 = minuto1;
    }

    public String getDia2() {
        return dia2;
    }

    public void setDia2(String dia2) {
        this.dia2 = dia2;
    }

    public String getMes2() {
        return mes2;
    }

    public void setMes2(String mes2) {
        this.mes2 = mes2;
    }

    public String getAnio2() {
        return anio2;
    }

    public void setAnio2(String anio2) {
        this.anio2 = anio2;
    }

    public String getHora2() {
        return hora2;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2;
    }

    public String getMinuto2() {
        return minuto2;
    }

    public void setMinuto2(String minuto2) {
        this.minuto2 = minuto2;
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

    public int getSubido1() {
        return subido1;
    }

    public void setSubido1(int subido1) {
        this.subido1 = subido1;
    }

    public int getSubido2() {
        return subido2;
    }

    public void setSubido2(int subido2) {
        this.subido2 = subido2;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.fecha_de_registro_id,_id);
        contentValues.put(SQLConstantes.fecha_de_registro_codigo,codigo);
        contentValues.put(SQLConstantes.fecha_de_registro_sede,sede);
        contentValues.put(SQLConstantes.fecha_de_registro_id_local,id_local);
        contentValues.put(SQLConstantes.fecha_de_registro_nom_local,nom_local);
        contentValues.put(SQLConstantes.fecha_de_registro_aula,aula);
        contentValues.put(SQLConstantes.fecha_de_registro_nombres,nombres);
        contentValues.put(SQLConstantes.fecha_de_registro_dia1,dia1);
        contentValues.put(SQLConstantes.fecha_de_registro_mes1,mes1);
        contentValues.put(SQLConstantes.fecha_de_registro_anio1,anio1);
        contentValues.put(SQLConstantes.fecha_de_registro_hora1,hora1);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto1,minuto1);
        contentValues.put(SQLConstantes.fecha_de_registro_dia2,dia2);
        contentValues.put(SQLConstantes.fecha_de_registro_mes2,mes2);
        contentValues.put(SQLConstantes.fecha_de_registro_anio2,anio2);
        contentValues.put(SQLConstantes.fecha_de_registro_hora2,hora2);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto2,minuto2);
        contentValues.put(SQLConstantes.fecha_de_registro_estado1,estado1);
        contentValues.put(SQLConstantes.fecha_de_registro_estado2,estado2);
        contentValues.put(SQLConstantes.fecha_de_registro_subido1,subido1);
        contentValues.put(SQLConstantes.fecha_de_registro_subido2,subido2);
        return contentValues;
    }
}
