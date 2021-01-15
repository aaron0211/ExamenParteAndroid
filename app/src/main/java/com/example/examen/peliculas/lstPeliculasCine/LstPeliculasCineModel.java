package com.example.examen.peliculas.lstPeliculasCine;

import android.os.AsyncTask;

import com.example.examen.beans.Pelicula;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LstPeliculasCineModel implements LstPeliculasCineContract.Model{
    private ArrayList<Pelicula> lsArrayPeliculas;
    OnLstPeliculasCineListener onLstPeliculasCineListener;

    @Override
    public void getPeliculasWS(OnLstPeliculasCineListener onLstPeliculasCineListener, String nombre) {
        this.onLstPeliculasCineListener = onLstPeliculasCineListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "PELICULA.CINE");
        param.put("CINE", nombre);

        DatosAPI dapi = new DatosAPI(param);
        dapi.execute("http://192.168.18.7:8084/Android/Controller");
    }

    class DatosAPI extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;

        public DatosAPI(HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String url_select = strings[0];
            try {
                Post post = new Post();
                JSONArray lstPeliculas = post.getServerDataPost(parametros, url_select);
                lsArrayPeliculas = Pelicula.getArrayListFromJSON(lstPeliculas);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                if (lsArrayPeliculas!=null && lsArrayPeliculas.size()>0){
                    onLstPeliculasCineListener.resolve(lsArrayPeliculas);
                }
            }else {
                onLstPeliculasCineListener.reject("Fallo al conectar con el Servidor. ");
            }
        }
    }
}
