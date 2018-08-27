package com.example.dmorales.evaluacion2018.modelo;

public class UsuarioLocal {

    private String _id;
    private String usuario;
    private String clave;
    private String nro_local;
    private String local_aplicacion;
    private String cod_sede_reg;
    private String sede_region;
    private String cod_nivel;
    private String nom_nivel;
    private String fase;

    public UsuarioLocal(String _id, String usuario, String clave, String nro_local, String local_aplicacion, String cod_sede_reg, String sede_region, String cod_nivel, String nom_nivel, String fase) {
        this._id = _id;
        this.usuario = usuario;
        this.clave = clave;
        this.nro_local = nro_local;
        this.local_aplicacion = local_aplicacion;
        this.cod_sede_reg = cod_sede_reg;
        this.sede_region = sede_region;
        this.cod_nivel = cod_nivel;
        this.nom_nivel = nom_nivel;
        this.fase = fase;
    }

    public UsuarioLocal() {
        this._id = "";
        this.usuario = "";
        this.clave = "";
        this.nro_local = "";
        this.local_aplicacion = "";
        this.cod_sede_reg = "";
        this.sede_region = "";
        this.cod_nivel = "";
        this.nom_nivel = "";
        this.fase = "";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNro_local() {
        return nro_local;
    }

    public void setNro_local(String nro_local) {
        this.nro_local = nro_local;
    }

    public String getLocal_aplicacion() {
        return local_aplicacion;
    }

    public void setLocal_aplicacion(String local_aplicacion) {
        this.local_aplicacion = local_aplicacion;
    }

    public String getCod_sede_reg() {
        return cod_sede_reg;
    }

    public void setCod_sede_reg(String cod_sede_reg) {
        this.cod_sede_reg = cod_sede_reg;
    }

    public String getSede_region() {
        return sede_region;
    }

    public void setSede_region(String sede_region) {
        this.sede_region = sede_region;
    }

    public String getCod_nivel() {
        return cod_nivel;
    }

    public void setCod_nivel(String cod_nivel) {
        this.cod_nivel = cod_nivel;
    }

    public String getNom_nivel() {
        return nom_nivel;
    }

    public void setNom_nivel(String nom_nivel) {
        this.nom_nivel = nom_nivel;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }
}
