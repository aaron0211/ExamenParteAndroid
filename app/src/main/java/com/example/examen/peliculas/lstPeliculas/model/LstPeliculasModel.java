package com.example.examen.peliculas.lstPeliculas.model;

import android.os.AsyncTask;
import android.widget.AdapterView;

import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculas.contract.LstPeliculasContract;
import com.example.examen.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LstPeliculasModel implements LstPeliculasContract.Model{
    private ArrayList<Pelicula> lstArrayPeliculas;
    OnLstPeliculasListener onLstPeliculasListener;

    @Override
    public void getPeliculasWS(OnLstPeliculasListener onLstPeliculasListener) {
        this.onLstPeliculasListener = onLstPeliculasListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION","PELICULA.FIND_ALL");
        DatosAPI dapi = new DatosAPI(param);
        dapi.execute("http://192.168.18.7:8084/Android/Controller");
    }

    class DatosAPI extends AsyncTask<String, Integer, Boolean>{

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
                lstArrayPeliculas = Pelicula.getArrayListPeliculaFromJSON(lstPeliculas);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean respuesta) {
            if (respuesta){
                if (lstArrayPeliculas!=null && lstArrayPeliculas.size()>0){
                    onLstPeliculasListener.resolve(lstArrayPeliculas);
                }
            }else {
                onLstPeliculasListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }
}
