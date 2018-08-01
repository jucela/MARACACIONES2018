package com.example.dmorales.evaluacion2018.modelo;

public class Nacional {
    private String codigo;
    private String sede;
    private String id_local;
    private String nom_local;
    private String aula;
    private String nombres;
    private String cargo;
    private String estado1;
    private String estado2;


    public Nacional(String codigo, String sede, String id_local, String nom_local, String aula, String nombres, String cargo, String estado1, String estado2) {
        this.codigo = codigo;
        this.sede = sede;
        this.id_local = id_local;
        this.nom_local = nom_local;
        this.aula = aula;
        this.nombres = nombres;
        this.cargo = cargo;
        this.estado1 = estado1;
        this.estado2 = estado2;
    }

    public Nacional() {
        this.codigo = "";
        this.sede = "";
        this.id_local = "";
        this.nom_local = "";
        this.aula = "";
        this.nombres = "";
        this.cargo = "";
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
}
