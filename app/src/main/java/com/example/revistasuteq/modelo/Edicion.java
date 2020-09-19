package com.example.revistasuteq.modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Edicion {
    private String idIS, tituloIS,volumenIS,numeroIS, fechaIS,añoIS,doiIS, imgIS;

    public Edicion( ) {

    }

    public Edicion(String idIS, String tituloIS, String volumenIS, String numeroIS, String fechaIS, String añoIS, String doiIS, String imgIS) {
        this.idIS = idIS;
        this.tituloIS = tituloIS;
        this.volumenIS = volumenIS;
        this.numeroIS = numeroIS;
        this.fechaIS = fechaIS;
        this.añoIS = añoIS;
        this.doiIS = doiIS;
        this.imgIS = imgIS;
    }

    public String getIdIS() {
        return idIS;
    }

    public void setIdIS(String idIS) {
        this.idIS = idIS;
    }

    public String getTituloIS() {
        return tituloIS;
    }

    public void setTituloIS(String tituloIS) {
        this.tituloIS = tituloIS;
    }

    public String getVolumenIS() {
        return volumenIS;
    }

    public void setVolumenIS(String volumenIS) {
        this.volumenIS = volumenIS;
    }

    public String getNumeroIS() {
        return numeroIS;
    }

    public void setNumeroIS(String numeroIS) {
        this.numeroIS = numeroIS;
    }

    public String getFechaIS() {
        return fechaIS;
    }

    public void setFechaIS(String fechaIS) {
        this.fechaIS = fechaIS;
    }

    public String getAñoIS() {
        return añoIS;
    }

    public void setAñoIS(String añoIS) {
        this.añoIS = añoIS;
    }

    public String getDoiIS() {
        return doiIS;
    }

    public void setDoiIS(String doiIS) {
        this.doiIS = doiIS;
    }

    public String getImgIS() {
        return imgIS;
    }

    public void setImgIS(String imgIS) {
        this.imgIS = imgIS;
    }
    public Edicion(JSONObject item) throws JSONException {
        this.idIS = item.getString("issue_id");
        this.tituloIS = item.getString("title");
        this.volumenIS = item.getString("volume");
        this.numeroIS = item.getString("number");
        this.fechaIS =item.getString("date_published");
        this.añoIS = item.getString("year");
        this.doiIS = item.getString("doi");
        this.imgIS = item.getString("cover");
    }
    public static ArrayList<Edicion> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Edicion> edicions = new ArrayList<>();

        for (int i = 0; i < datos.length() ; i++) {
            edicions.add(new Edicion(datos.getJSONObject(i)));
        }
        return edicions;
    }
}
