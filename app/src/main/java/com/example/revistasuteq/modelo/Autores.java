package com.example.revistasuteq.modelo;

public class Autores {
    private String autores,filiacion,email;

    public Autores() {

    }

    public Autores(String autores, String filiacion, String email) {
        this.autores = autores;
        this.filiacion = filiacion;
        this.email = email;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(String filiacion) {
        this.filiacion = filiacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
