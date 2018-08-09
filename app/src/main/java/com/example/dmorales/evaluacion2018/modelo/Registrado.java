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
    private String dia3;
    private String mes3;
    private String anio3;
    private String hora3;
    private String minuto3;
    private String dia4;
    private String mes4;
    private String anio4;
    private String hora4;
    private String minuto4;
    private String estado1;
    private String estado2;
    private String estado3;
    private String estado4;
    private int subido1;
    private int subido2;
    private int subido3;
    private int subido4;


    public Registrado(String _id, String codigo,String sede, String id_local, String nom_local, String aula, String nombres, String dia1, String mes1, String anio1, String hora1, String minuto1, String dia2, String mes2, String anio2, String hora2, String minuto2, String dia3, String mes3, String anio3, String hora3, String minuto3, String dia4, String mes4, String anio4, String hora4, String minuto4, String estado1, String estado2, String estado3, String estado4, int subido1, int subido2, int subido3, int subido4) {
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
        this.dia3 = dia3;
        this.mes3 = mes3;
        this.anio3 = anio3;
        this.hora3 = hora3;
        this.minuto3 = minuto3;
        this.dia4 = dia4;
        this.mes4 = mes4;
        this.anio4 = anio4;
        this.hora4 = hora4;
        this.minuto4 = minuto4;
        this.estado1 = estado1;
        this.estado2 = estado2;
        this.estado3 = estado3;
        this.estado4 = estado4;
        this.subido1 = subido1;
        this.subido2 = subido2;
        this.subido3 = subido3;
        this.subido4 = subido4;
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
        this.dia3 = "";
        this.mes3 = "";
        this.anio3 = "";
        this.hora3 = "";
        this.minuto3 = "";
        this.dia4 = "";
        this.mes4 = "";
        this.anio4 = "";
        this.hora4 = "";
        this.minuto4 = "";
        this.estado1 = "";
        this.estado2 = "";
        this.estado3 = "";
        this.estado4 = "";
        this.subido1 = 0;
        this.subido2 = 0;
        this.subido3 = 0;
        this.subido4 = 0;

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

    public void setSede(String id_sede) {
        this.sede = id_sede;
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

    public String getDia3() {
        return dia3;
    }

    public void setDia3(String dia3) {
        this.dia3 = dia3;
    }

    public String getMes3() {
        return mes3;
    }

    public void setMes3(String mes3) {
        this.mes3 = mes3;
    }

    public String getAnio3() {
        return anio3;
    }

    public void setAnio3(String anio3) {
        this.anio3 = anio3;
    }

    public String getHora3() {
        return hora3;
    }

    public void setHora3(String hora3) {
        this.hora3 = hora3;
    }

    public String getMinuto3() {
        return minuto3;
    }

    public void setMinuto3(String minuto3) {
        this.minuto3 = minuto3;
    }

    public String getDia4() {
        return dia4;
    }

    public void setDia4(String dia4) {
        this.dia4 = dia4;
    }

    public String getMes4() {
        return mes4;
    }

    public void setMes4(String mes4) {
        this.mes4 = mes4;
    }

    public String getAnio4() {
        return anio4;
    }

    public void setAnio4(String anio4) {
        this.anio4 = anio4;
    }

    public String getHora4() {
        return hora4;
    }

    public void setHora4(String hora4) {
        this.hora4 = hora4;
    }

    public String getMinuto4() {
        return minuto4;
    }

    public void setMinuto4(String minuto4) {
        this.minuto4 = minuto4;
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

    public String getEstado3() {
        return estado3;
    }

    public void setEstado3(String estado3) {
        this.estado3 = estado3;
    }

    public String getEstado4() {
        return estado4;
    }

    public void setEstado4(String estado4) {
        this.estado4 = estado4;
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

    public int getSubido3() {
        return subido3;
    }

    public void setSubido3(int subido3) {
        this.subido3 = subido3;
    }

    public int getSubido4() {
        return subido4;
    }

    public void setSubido4(int subido4) {
        this.subido4 = subido4;
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
        contentValues.put(SQLConstantes.fecha_de_registro_dia3,dia3);
        contentValues.put(SQLConstantes.fecha_de_registro_mes3,mes3);
        contentValues.put(SQLConstantes.fecha_de_registro_anio3,anio3);
        contentValues.put(SQLConstantes.fecha_de_registro_hora3,hora3);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto3,minuto3);
        contentValues.put(SQLConstantes.fecha_de_registro_dia4,dia4);
        contentValues.put(SQLConstantes.fecha_de_registro_mes4,mes4);
        contentValues.put(SQLConstantes.fecha_de_registro_anio4,anio4);
        contentValues.put(SQLConstantes.fecha_de_registro_hora4,hora4);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto4,minuto4);
        contentValues.put(SQLConstantes.fecha_de_registro_estado1,estado1);
        contentValues.put(SQLConstantes.fecha_de_registro_estado2,estado2);
        contentValues.put(SQLConstantes.fecha_de_registro_estado3,estado3);
        contentValues.put(SQLConstantes.fecha_de_registro_estado4,estado4);
        contentValues.put(SQLConstantes.fecha_de_registro_subido1,subido1);
        contentValues.put(SQLConstantes.fecha_de_registro_subido2,subido2);
        contentValues.put(SQLConstantes.fecha_de_registro_subido3,subido3);
        contentValues.put(SQLConstantes.fecha_de_registro_subido4,subido4);
        return contentValues;
    }
}
