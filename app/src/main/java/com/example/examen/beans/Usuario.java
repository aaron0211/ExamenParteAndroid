package com.example.examen.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
    private static final String ID = "idUsuario";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDO = "apellido";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FECHA_REGISTRO = "fechaRegistro";

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String fecha_registro;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public static ArrayList<Usuario> getArrayListFromJSON(JSONArray lstUsuarios) {
        ArrayList<Usuario> lista = null;
        try {
            if (lstUsuarios != null && lstUsuarios.length() > 0) {
                lista = new ArrayList<Usuario>();
            }
            for (int i = 0; i < lstUsuarios.length(); i++) {
                JSONObject json_data = lstUsuarios.getJSONObject(i);
                Usuario usuario = new Usuario();

                usuario.setId(json_data.getInt(ID));
                usuario.setNombre(json_data.getString(NOMBRE));
                usuario.setApellido(json_data.getString(APELLIDO));
                usuario.setEmail(json_data.getString(EMAIL));
                usuario.setPassword(json_data.getString(PASSWORD));
                usuario.setFecha_registro(json_data.getString(FECHA_REGISTRO));

                lista.add(usuario);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
