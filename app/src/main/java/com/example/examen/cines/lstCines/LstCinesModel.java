package com.example.examen.cines.lstCines;

import android.os.AsyncTask;

import com.example.examen.beans.Cine;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LstCinesModel implements LstCinesContract.Model{
    //private static final String URL="https://jsonblob.com/api/567eee7e-50d4-11eb-9b3f-89785423a085";
    ArrayList<Cine> lstArrayCines;
    OnLstCinesListener onLstCinesListener;

    @Override
    public void getCinesWS(OnLstCinesListener onLstCinesListener) {
        this.onLstCinesListener = onLstCinesListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION","CINE.FIND_ALL");
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
                //JSONArray lstCines = post.getServerDataGet(URL);
                JSONArray lstCines = post.getServerDataPost(parametros,url_select);
                lstArrayCines = Cine.getArrayListFromJSON(lstCines);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                if (lstArrayCines!=null && lstArrayCines.size()>0){
                    onLstCinesListener.resolve(lstArrayCines);
                }
            }else {
                onLstCinesListener.reject("Error al cargar la lista de cines. ");
            }
        }
    }
}
