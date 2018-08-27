package com.example.dmorales.evaluacion2018.modelo;

public class Nacional {
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


    public Nacional(String _id, String sede, int nro_local, String local_aplicacion, String direccion_local, String cod_sede_reg, String cod_sede_prov, String cod_sede_distrital, String sede_reg, String sede_prov, String sede_distrital, String aula, int bungalow, String apepat, String numdoc, String tipo, int tipocargo, String cargo, int nivel, int responsable_bungalow) {
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
    }

    public Nacional(){
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
}
