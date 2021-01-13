package com.example.examen.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cine {

    private static final String ID = "id_cine";
    private static final String NOMBRE = "nombre";
    private static final String LOCALIDAD = "localidad";

    private int id;
    private String nombre;
    private String localidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public static ArrayList<Cine> getArrayListFromJSON(JSONArray lstCine){
        ArrayList<Cine> lista = null;
        try {
            if (lstCine!= null && lstCine.length()>0){
                lista = new ArrayList<Cine>();
            }
            for (int i=0;i<lstCine.length();i++){
                JSONObject json_data = lstCine.getJSONObject(i);
                Cine cine = new Cine();

                cine.setId(json_data.getInt(ID));
                cine.setNombre(json_data.getString(NOMBRE));
                cine.setLocalidad(json_data.getString(LOCALIDAD));

                lista.add(cine);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  lista;
    }
}
