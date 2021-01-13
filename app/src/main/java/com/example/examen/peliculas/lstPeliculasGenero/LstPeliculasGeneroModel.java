package com.example.examen.peliculas.lstPeliculasGenero;

import android.os.AsyncTask;

import com.example.examen.beans.Pelicula;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LstPeliculasGeneroModel implements LstPeliculasGeneroContract.Model{
    private ArrayList<Pelicula> lstArrayPeliculas;
    OnLstPeliculasGeneroListener onLstPeliculasGeneroListener;

    @Override
    public void getPeliculasGeneroWS(OnLstPeliculasGeneroListener onLstPeliculasGeneroListener, String genero) {
        this.onLstPeliculasGeneroListener = onLstPeliculasGeneroListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION","PELICULA.FIND_ALL");
        param.put("FILTRO","GENERO."+genero);
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
                    onLstPeliculasGeneroListener.resolve(lstArrayPeliculas);
                }
            }else {
                onLstPeliculasGeneroListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }
}
