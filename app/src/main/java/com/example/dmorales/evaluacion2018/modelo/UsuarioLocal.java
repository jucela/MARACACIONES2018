package com.example.dmorales.evaluacion2018.modelo;

public class UsuarioLocal {
//    private String usuario;
//    private String clave;
//    private String nombrelocal;
//    private String sede;

    private String usuario;
    private String clave;
    private String nom_local;
    private String sede_region;

    public UsuarioLocal(String usuario, String clave, String nom_local, String sede_region) {
        this.usuario = usuario;
        this.clave = clave;
        this.nom_local = nom_local;
        this.sede_region = sede_region;
    }

    public UsuarioLocal() {
        this.usuario = "";
        this.clave = "";
        this.nom_local = "";
        this.sede_region = "";
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

    public String getNom_local() {
        return nom_local;
    }

    public void setNom_local(String nom_local) {
        this.nom_local = nom_local;
    }

    public String getSede_region() {
        return sede_region;
    }

    public void setSede_region(String sede_region) {
        this.sede_region = sede_region;
    }
}
