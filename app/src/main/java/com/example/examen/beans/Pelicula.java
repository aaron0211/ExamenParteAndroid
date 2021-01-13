package com.example.examen.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pelicula {
    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String PRECIO = "precio";
    private static final String DURACION = "idGenero";
    private static final String TRAILER = "trailer";
    private static final String SINOPSIS = "sinopsis";
    private static final String N_VOTOS = "nVotos";
    private static final String S_PUNTUACION = "sPuntuacion";
    private static final String FECHA_ESTRENO = "fechaEstreno";
    private static final String URL = "url";
    private static final String FECHA = "fecha";
    private static final String HORA = "hora";
    private static final String CINE = "cine";
    private static final String LOCALIDAD = "localidad";

    private int id;
    private String titulo;
    private String precio;
    private int duracion;
    private String trailer;
    private String sinopsis;
    private int n_votos;
    private int s_puntuacion;
    private String f_estreno;
    private String url;
    private String fecha;
    private String hora;
    private String cine;
    private String localidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        String video = trailer.replace("https://www.youtube.com/watch?v=","");
        this.trailer = video;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getN_votos() {
        return n_votos;
    }

    public void setN_votos(int n_votos) {
        this.n_votos = n_votos;
    }

    public int getS_puntuacion() {
        return s_puntuacion;
    }

    public void setS_puntuacion(int s_puntuacion) {
        this.s_puntuacion = s_puntuacion;
    }

    public String getF_estreno() {
        return f_estreno;
    }

    public void setF_estreno(String f_estreno) {
        this.f_estreno = f_estreno;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCine() {
        return cine;
    }

    public void setCine(String cine) {
        this.cine = cine;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public static ArrayList<Pelicula> getArrayListFromJSON(JSONArray lstPeliculas){
        ArrayList<Pelicula> lista = null;
        try {
            if (lstPeliculas!= null && lstPeliculas.length()>0){
                lista = new ArrayList<Pelicula>();
            }
            for (int i=0;i<lstPeliculas.length();i++){
                JSONObject json_data = lstPeliculas.getJSONObject(i);
                Pelicula pelicula = new Pelicula();

                pelicula.setId(json_data.getInt(ID));
                pelicula.setTitulo(json_data.getString(TITULO));
                pelicula.setPrecio(json_data.getString(PRECIO));
                pelicula.setDuracion(json_data.getInt(DURACION));
                pelicula.setTrailer(json_data.getString(TRAILER));
                pelicula.setSinopsis(json_data.getString(SINOPSIS));
                pelicula.setN_votos(json_data.getInt(N_VOTOS));
                pelicula.setS_puntuacion(json_data.getInt(S_PUNTUACION));
                pelicula.setF_estreno(json_data.getString(FECHA_ESTRENO));
                pelicula.setUrl(json_data.getString(URL));
                pelicula.setFecha(json_data.getString(FECHA));
                pelicula.setHora(json_data.getString(HORA));

                lista.add(pelicula);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  lista;
    }

    public static ArrayList<Pelicula> getArrayListPeliculaFromJSON(JSONArray lstPeliculas){
        ArrayList<Pelicula> lista = null;
        try {
            if (lstPeliculas!= null && lstPeliculas.length()>0){
                lista = new ArrayList<Pelicula>();
            }
            for (int i=0;i<lstPeliculas.length();i++){
                JSONObject json_data = lstPeliculas.getJSONObject(i);
                Pelicula pelicula = new Pelicula();

                pelicula.setId(json_data.getInt(ID));
                pelicula.setTitulo(json_data.getString(TITULO));
                pelicula.setPrecio(json_data.getString(PRECIO));
                pelicula.setDuracion(json_data.getInt(DURACION));
                pelicula.setTrailer(json_data.getString(TRAILER));
                pelicula.setSinopsis(json_data.getString(SINOPSIS));
                pelicula.setN_votos(json_data.getInt(N_VOTOS));
                pelicula.setS_puntuacion(json_data.getInt(S_PUNTUACION));
                pelicula.setF_estreno(json_data.getString(FECHA_ESTRENO));
                pelicula.setUrl(json_data.getString(URL));

                lista.add(pelicula);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  lista;
    }

    public static ArrayList<Pelicula> getArrayListPeliculaSesionFromJSON(JSONArray lstPeliculas){
        ArrayList<Pelicula> lista = null;
        try {
            if (lstPeliculas!= null && lstPeliculas.length()>0){
                lista = new ArrayList<Pelicula>();
            }
            for (int i=0;i<lstPeliculas.length();i++){
                JSONObject json_data = lstPeliculas.getJSONObject(i);
                Pelicula pelicula = new Pelicula();

                pelicula.setId(json_data.getInt(ID));
                pelicula.setTitulo(json_data.getString(TITULO));
                pelicula.setPrecio(json_data.getString(PRECIO));
                pelicula.setDuracion(json_data.getInt(DURACION));
                pelicula.setTrailer(json_data.getString(TRAILER));
                pelicula.setSinopsis(json_data.getString(SINOPSIS));
                pelicula.setN_votos(json_data.getInt(N_VOTOS));
                pelicula.setS_puntuacion(json_data.getInt(S_PUNTUACION));
                pelicula.setF_estreno(json_data.getString(FECHA_ESTRENO));
                pelicula.setUrl(json_data.getString(URL));
                pelicula.setFecha(json_data.getString(FECHA));
                pelicula.setHora(json_data.getString(HORA));
                pelicula.setCine(json_data.getString(CINE));
                pelicula.setLocalidad(json_data.getString(LOCALIDAD));

                lista.add(pelicula);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  lista;
    }
}
