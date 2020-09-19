package com.example.revistasuteq.modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {
    private String nombreJ, descripJ,imgUrlJ,idJ;

    public String getIdJ() {
        return idJ;
    }

    public void setIdJ(String idJ) {
        this.idJ = idJ;
    }

    public Revista(String nombreJ, String descripJ, String imgUrlJ, String idJ) {
        this.nombreJ = nombreJ;
        this.descripJ = descripJ;
        this.imgUrlJ = imgUrlJ;
        this.idJ=idJ;
    }

    public Revista() {

    }

    public String getNombreJ() {
        return nombreJ;
    }

    public void setNombreJ(String nombreJ) {
        this.nombreJ = nombreJ;
    }

    public String getDescripJ() {
        return descripJ;
    }

    public void setDescripJ(String descripJ) {
        this.descripJ = descripJ;
    }

    public String getImgUrlJ() {
        return imgUrlJ;
    }

    public void setImgUrlJ(String imgUrlJ) {
        this.imgUrlJ = imgUrlJ;
    }
    public Revista(JSONObject item) throws JSONException {
        nombreJ= item.getString("name");
        descripJ= item.getString("description");
        imgUrlJ= item.getString("portada");
        idJ=item.getString("journal_id");
    }
    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();

        for (int i = 0; i < datos.length() ; i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }
}
