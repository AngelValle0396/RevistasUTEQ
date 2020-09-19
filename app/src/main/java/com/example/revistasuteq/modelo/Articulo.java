package com.example.revistasuteq.modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
    private String tituloA, doiA,abstractA,fechaA,urlPDFA,urlHTMLA,keywordsA,section;

    public Articulo( ) {

    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTituloA() {
        return tituloA;
    }

    public void setTituloA(String tituloA) {
        this.tituloA = tituloA;
    }

    public String getDoiA() {
        return doiA;
    }

    public void setDoiA(String doiA) {
        this.doiA = doiA;
    }

    public String getAbstractA() {
        return abstractA;
    }

    public void setAbstractA(String abstractA) {
        this.abstractA = abstractA;
    }

    public String getFechaA() {
        return fechaA;
    }

    public void setFechaA(String fechaA) {
        this.fechaA = fechaA;
    }

    public String getUrlPDFA() {
        return urlPDFA;
    }

    public void setUrlPDFA(String urlPDFA) {
        this.urlPDFA = urlPDFA;
    }

    public String getUrlHTMLA() {
        return urlHTMLA;
    }

    public void setUrlHTMLA(String urlHTMLA) {
        this.urlHTMLA = urlHTMLA;
    }

    public String getKeywordsA() {
        return keywordsA;
    }

    public void setKeywordsA(String keywordsA) {
        this.keywordsA = keywordsA;
    }

    public Articulo(String tituloA, String doiA, String abstractA, String fechaA, String urlPDFA, String urlHTMLA, String keywordsA,String section) {
        this.tituloA = tituloA;
        this.doiA = doiA;
        this.abstractA = abstractA;
        this.fechaA = fechaA;
        this.urlPDFA = urlPDFA;
        this.urlHTMLA = urlHTMLA;
        this.keywordsA = keywordsA;
        this.section=section;
    }
    public Articulo(JSONObject item) throws JSONException {

        this.tituloA =item.getString("title");
        this.doiA = item.getString("doi");
        this.abstractA = item.getString("abstract");
        this.fechaA = item.getString("date_published");
        this.urlPDFA = item.getString("section");
        this.urlHTMLA = item.getString("section");
        this.keywordsA = item.getString("section");
        this.section=item.getString("section");

    }
    public static ArrayList<Articulo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Articulo> articulos = new ArrayList<>();

        for (int i = 0; i < datos.length() ; i++) {
            articulos.add(new Articulo(datos.getJSONObject(i)));
        }
        return articulos;
    }
    public static ArrayList<Categoria> JsonObjectBuild(List<Articulo> articulos){
        ArrayList<Categoria> seccion2Revistas = new ArrayList<>();
        ArrayList<String> secciones= new ArrayList<>();
        String seccion="";
        String aux_seccion="";
        for (int i = 0; i < articulos.size() ; i++) {
            seccion=articulos.get(i).getSection();
            if(!seccion.equals(aux_seccion)){
                secciones.add(seccion);
            }
            aux_seccion=seccion;
        }
        for (int i=0;i< secciones.size();i++){
            List<Articulo> aux_articulos= new ArrayList<>();
            for (int j = 0; j < articulos.size() ; j++) {
                if(secciones.get(i).equals(articulos.get(j).getSection())){
                    aux_articulos.add(new Articulo(
                            articulos.get(j).getTituloA(),
                            articulos.get(j).getDoiA(),
                            articulos.get(j).getAbstractA(),
                            articulos.get(j).getFechaA(),
                            articulos.get(j).getUrlPDFA(),
                            articulos.get(j).getUrlHTMLA(),
                            articulos.get(j).getKeywordsA(),
                            articulos.get(j).getSection()
                    ));
                }
            }
            seccion2Revistas.add(new Categoria(secciones.get(i), aux_articulos));
        }
        return seccion2Revistas;
    }
}
