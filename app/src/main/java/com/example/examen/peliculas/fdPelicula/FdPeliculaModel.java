package com.example.examen.peliculas.fdPelicula;

import android.os.AsyncTask;

import com.example.examen.beans.Pelicula;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class FdPeliculaModel implements FdPeliculaContract.Model{
    private ArrayList<Pelicula> lstArrayPeliculas;
    OnFdPeliculasListener onFdPeliculasListener;

    @Override
    public void getSesionesWS(OnFdPeliculasListener onFdPeliculasListener, String titulo) {
        this.onFdPeliculasListener = onFdPeliculasListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION","PELICULA.PELICULA");
        param.put("TITULO",titulo);
        DatosAPI dapi = new DatosAPI(param);
        dapi.execute("http://192.168.18.7:8084/Android/Controller");
    }

    class DatosAPI extends AsyncTask<String, Integer, Boolean> {

        private HashMap<String,String> parametros = null;
        public DatosAPI(HashMap<String, String> parametros){
            super();
            this.parametros = parametros;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String url_select = strings[0];
            try{
                Post post = new Post();
                JSONArray lstPeliculas = post.getServerDataPost(parametros,url_select);
                lstArrayPeliculas = Pelicula.getArrayListPeliculaSesionFromJSON(lstPeliculas);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean respuesta) {
            if (respuesta){
                if (lstArrayPeliculas!=null && lstArrayPeliculas.size()>0){
                    onFdPeliculasListener.resolve(lstArrayPeliculas);
                }
            }else {
                onFdPeliculasListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }
}
