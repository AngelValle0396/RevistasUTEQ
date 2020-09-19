package com.example.revistasuteq.modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String sectionC, idC;
    private List<Articulo> articuloList;

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public Categoria(String sectionC, String idC, List<Articulo> articuloList) {
        this.sectionC = sectionC;
        this.idC = idC;
        this.articuloList = articuloList;
    }

    public Categoria(String sectionC, List<Articulo> articuloList) {
        this.sectionC = sectionC;
        this.articuloList = articuloList;
    }

    public Categoria(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public Categoria( ) {

    }

    public String getSectionC() {
        return sectionC;
    }

    public void setSectionC(String sectionC) {
        this.sectionC = sectionC;
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }
    public List<Articulo> getArticuloList() {
        return articuloList;
    }
    public Categoria(JSONObject item) throws JSONException {

        this.sectionC = item.getString("section");
        this.idC = item.getString("section_id");
    }
    public static ArrayList<Categoria> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Categoria> categorias = new ArrayList<>();

        for (int i = 0; i < datos.length() ; i++) {
            categorias.add(new Categoria(datos.getJSONObject(i)));
        }
        return categorias;
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
                            articulos.get(j).getSection(),
                            articulos.get(j).getAutoresA()
                    ));
                }
            }
            seccion2Revistas.add(new Categoria(secciones.get(i), aux_articulos));
        }
        return seccion2Revistas;
    }
}
