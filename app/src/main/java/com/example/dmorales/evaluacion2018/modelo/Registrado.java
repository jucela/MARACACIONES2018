package com.example.dmorales.evaluacion2018.modelo;

import android.content.ContentValues;

public class Registrado {
    private String _id;
    private String sede;
    private int nro_local;
    private String local_aplicacion;
    private String direccion_local;
    private String cod_sede_reg;
    private String cod_sede_prov;
    private String cod_sede_distrital;
    private String sede_reg;
    private String sede_prov;
    private String sede_distrital;
    private String aula;
    private int bungalow;
    private String apepat;
    private String numdoc;
    private String tipo;
    private int tipocargo;
    private String cargo;
    private int nivel;
    private int responsable_bungalow;
    private int estatus1;
    private int dia1;
    private int mes1;
    private int anio1;
    private int hora1;
    private int minuto1;
    private int estatus2;
    private int dia2;
    private int mes2;
    private int anio2;
    private int hora2;
    private int minuto2;
    private int subido1;
    private int subido2;

    public Registrado(String _id, String sede, int nro_local, String local_aplicacion, String direccion_local, String cod_sede_reg, String cod_sede_prov, String cod_sede_distrital, String sede_reg, String sede_prov, String sede_distrital, String aula, int bungalow, String apepat, String numdoc, String tipo, int tipocargo, String cargo, int nivel, int responsable_bungalow, int estatus1, int dia1, int mes1, int anio1, int hora1, int minuto1, int estatus2, int dia2, int mes2, int anio2, int hora2, int minuto2, int subido1, int subido2) {
        this._id = _id;
        this.sede = sede;
        this.nro_local = nro_local;
        this.local_aplicacion = local_aplicacion;
        this.direccion_local = direccion_local;
        this.cod_sede_reg = cod_sede_reg;
        this.cod_sede_prov = cod_sede_prov;
        this.cod_sede_distrital = cod_sede_distrital;
        this.sede_reg = sede_reg;
        this.sede_prov = sede_prov;
        this.sede_distrital = sede_distrital;
        this.aula = aula;
        this.bungalow = bungalow;
        this.apepat = apepat;
        this.numdoc = numdoc;
        this.tipo = tipo;
        this.tipocargo = tipocargo;
        this.cargo = cargo;
        this.nivel = nivel;
        this.responsable_bungalow = responsable_bungalow;
        this.estatus1 = estatus1;
        this.dia1 = dia1;
        this.mes1 = mes1;
        this.anio1 = anio1;
        this.hora1 = hora1;
        this.minuto1 = minuto1;
        this.estatus2 = estatus2;
        this.dia2 = dia2;
        this.mes2 = mes2;
        this.anio2 = anio2;
        this.hora2 = hora2;
        this.minuto2 = minuto2;
        this.subido1 = subido1;
        this.subido2 = subido2;
    }

    public Registrado(){
        this._id="";
        this.sede="";
        this.nro_local=0;
        this.local_aplicacion="";
        this.direccion_local ="";
        this.cod_sede_reg="";
        this.cod_sede_prov="";
        this.cod_sede_distrital="";
        this.sede_reg="";
        this.sede_prov="";
        this.sede_distrital="";
        this.aula="";
        this.bungalow = 0;
        this.apepat="";
        this.numdoc="";
        this.tipo = "";
        this.tipocargo=0;
        this.cargo="";
        this.nivel=0;
        this.responsable_bungalow = 0;
        this.estatus1=0;
        this.dia1=0;
        this.mes1=0;
        this.anio1=0;
        this.hora1=0;
        this.minuto1=0;
        this.estatus2=0;
        this.dia2=0;
        this.mes2=0;
        this.anio2=0;
        this.hora2=0;
        this.minuto2=0;
        this.subido1 = 0;
        this.subido2 = 0;
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

    public String getCod_sede_reg() {
        return cod_sede_reg;
    }

    public void setCod_sede_reg(String cod_sede_reg) {
        this.cod_sede_reg = cod_sede_reg;
    }

    public String getCod_sede_prov() {
        return cod_sede_prov;
    }

    public void setCod_sede_prov(String cod_sede_prov) {
        this.cod_sede_prov = cod_sede_prov;
    }

    public String getCod_sede_distrital() {
        return cod_sede_distrital;
    }

    public void setCod_sede_distrital(String cod_sede_distrital) {
        this.cod_sede_distrital = cod_sede_distrital;
    }

    public String getSede_reg() {
        return sede_reg;
    }

    public void setSede_reg(String sede_reg) {
        this.sede_reg = sede_reg;
    }

    public String getSede_prov() {
        return sede_prov;
    }

    public void setSede_prov(String sede_prov) {
        this.sede_prov = sede_prov;
    }

    public String getSede_distrital() {
        return sede_distrital;
    }

    public void setSede_distrital(String sede_distrital) {
        this.sede_distrital = sede_distrital;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public int getBungalow() {
        return bungalow;
    }

    public void setBungalow(int bungalow) {
        this.bungalow = bungalow;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTipocargo() {
        return tipocargo;
    }

    public void setTipocargo(int tipocargo) {
        this.tipocargo = tipocargo;
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

    public int getResponsable_bungalow() {
        return responsable_bungalow;
    }

    public void setResponsable_bungalow(int responsable_bungalow) {
        this.responsable_bungalow = responsable_bungalow;
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

    public int getEstatus1() {
        return estatus1;
    }

    public void setEstatus1(int estatus1) {
        this.estatus1 = estatus1;
    }

    public int getEstatus2() {
        return estatus2;
    }

    public void setEstatus2(int estatus2) {
        this.estatus2 = estatus2;
    }

    public int getDia2() {
        return dia2;
    }

    public void setDia2(int dia2) {
        this.dia2 = dia2;
    }

    public int getMes2() {
        return mes2;
    }

    public void setMes2(int mes2) {
        this.mes2 = mes2;
    }

    public int getAnio2() {
        return anio2;
    }

    public void setAnio2(int anio2) {
        this.anio2 = anio2;
    }

    public int getHora2() {
        return hora2;
    }

    public void setHora2(int hora2) {
        this.hora2 = hora2;
    }

    public int getMinuto2() {
        return minuto2;
    }

    public void setMinuto2(int minuto2) {
        this.minuto2 = minuto2;
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
        contentValues.put(SQLConstantes.fecha_de_registro_sede,sede);
        contentValues.put(SQLConstantes.fecha_de_registro_nro_local,nro_local);
        contentValues.put(SQLConstantes.fecha_de_registro_local_aplicacion,local_aplicacion);
        contentValues.put(SQLConstantes.fecha_de_registro_direccion_local,direccion_local);
        contentValues.put(SQLConstantes.fecha_de_registro_cod_sede_reg,cod_sede_reg);
        contentValues.put(SQLConstantes.fecha_de_registro_cod_sede_prov,cod_sede_prov);
        contentValues.put(SQLConstantes.fecha_de_registro_cod_sede_distrital,cod_sede_distrital);
        contentValues.put(SQLConstantes.fecha_de_registro_sede_reg,sede_reg);
        contentValues.put(SQLConstantes.fecha_de_registro_sede_prov,sede_prov);
        contentValues.put(SQLConstantes.fecha_de_registro_sede_distrital,sede_distrital);
        contentValues.put(SQLConstantes.fecha_de_registro_aula,aula);
        contentValues.put(SQLConstantes.fecha_de_registro_bungalow,bungalow);
        contentValues.put(SQLConstantes.fecha_de_registro_apepat,apepat);
        contentValues.put(SQLConstantes.fecha_de_registro_numdoc,numdoc);
        contentValues.put(SQLConstantes.fecha_de_registro_tipo,tipo);
        contentValues.put(SQLConstantes.fecha_de_registro_tipocargo,tipocargo);
        contentValues.put(SQLConstantes.fecha_de_registro_cargo,cargo);
        contentValues.put(SQLConstantes.fecha_de_registro_nivel,nivel);
        contentValues.put(SQLConstantes.fecha_de_registro_responsable_bungalow,responsable_bungalow);
        contentValues.put(SQLConstantes.fecha_de_registro_estatus1,estatus1);
        contentValues.put(SQLConstantes.fecha_de_registro_dia1,dia1);
        contentValues.put(SQLConstantes.fecha_de_registro_mes1,mes1);
        contentValues.put(SQLConstantes.fecha_de_registro_anio1,anio1);
        contentValues.put(SQLConstantes.fecha_de_registro_hora1,hora1);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto1,minuto1);
        contentValues.put(SQLConstantes.fecha_de_registro_estatus2,estatus2);
        contentValues.put(SQLConstantes.fecha_de_registro_dia2,dia2);
        contentValues.put(SQLConstantes.fecha_de_registro_mes2,mes2);
        contentValues.put(SQLConstantes.fecha_de_registro_anio2,anio2);
        contentValues.put(SQLConstantes.fecha_de_registro_hora2,hora2);
        contentValues.put(SQLConstantes.fecha_de_registro_minuto2,minuto2);
        contentValues.put(SQLConstantes.fecha_de_registro_subido1,subido1);
        contentValues.put(SQLConstantes.fecha_de_registro_subido2,subido2);
        return contentValues;
    }
}
