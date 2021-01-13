package com.example.examen.peliculas.lstPeliculasTOP;

import android.os.AsyncTask;

import com.example.examen.beans.Pelicula;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LstPeliculasTopModel implements LstPeliculasTopContract.Model{
    private ArrayList<Pelicula> lstArrayPeliculas;
    OnLstPeliculasTopListener onLstPeliculasTopListener;

    @Override
    public void getPeliculasTopWS(OnLstPeliculasTopListener onLstPeliculasTopListener) {
        this.onLstPeliculasTopListener = onLstPeliculasTopListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION","PELICULA.FIND_ALL");
        param.put("FILTRO","TOP10");
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
                System.out.println(parametros.toString()+url_select);
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
                    onLstPeliculasTopListener.resolve(lstArrayPeliculas);
                }
            }else {
                onLstPeliculasTopListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }

}
