package com.example.examen.peliculas.lstPeliculasCine;

import android.os.AsyncTask;

import com.example.examen.beans.Pelicula;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LstPeliculasCineModel implements LstPeliculasCineContract.Model{
    //private String URL="";
    private ArrayList<Pelicula> lsArrayPeliculas;
    OnLstPeliculasCineListener onLstPeliculasCineListener;

    @Override
    public void getPeliculasWS(OnLstPeliculasCineListener onLstPeliculasCineListener, String nombre) {
        this.onLstPeliculasCineListener = onLstPeliculasCineListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "PELICULA.CINE");
//        switch (nombre){
//            case "Cervantes":
//                URL = "https://jsonblob.com/api/4c5da603-5119-11eb-9b3f-b12ec5fa0f2f";
//                break;
//            case "Aragonia":
//                URL = "https://jsonblob.com/api/27f015f3-5119-11eb-9b3f-f7904b7e9dc5";
//                break;
//            case "Grancasa":
//                URL = "https://jsonblob.com/api/76c3d659-5119-11eb-9b3f-9b9afc672fb4";
//                break;
//            case "Maravillas":
//                URL = "https://jsonblob.com/api/9518e179-5119-11eb-9b3f-e97e79cb484f";
//                break;
//            case "Cinemundo":
//                URL = "https://jsonblob.com/api/b83c8c7b-5119-11eb-9b3f-cd11b02cd2cc";
//                break;
//        }
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
                //JSONArray lstPeliculas = post.getServerDataGet(URL);
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
