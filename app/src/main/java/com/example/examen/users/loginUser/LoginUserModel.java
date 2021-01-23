package com.example.examen.users.loginUser;

import android.os.AsyncTask;

import com.example.examen.beans.Pelicula;
import com.example.examen.beans.Usuario;
import com.example.examen.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginUserModel implements LoginUserContract.Model {
    private ArrayList<Usuario> lstArrayUsuarios;
    OnLoginUserListener onLoginUserListener;

    @Override
    public void getUsuarioWS(OnLoginUserListener onLoginUserListener, Usuario usuario) {
        this.onLoginUserListener = onLoginUserListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USUARIO.LOGIN");
        param.put("USER", usuario.getEmail());
        param.put("PASSWORD", usuario.getPassword());
        //http://192.168.18.7:8084/Android/Controller?
        // ACTION=USUARIO.LOGIN
        // &USER=aaron@gmail.com
        // &PASSWORD=123
        DatosAPI dapi = new DatosAPI(param);
        dapi.execute("http://192.168.18.7:8084/Android/Controller");
    }

    class DatosAPI extends AsyncTask<String, Integer, Boolean> {

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
                JSONArray lstUsuarios = post.getServerDataPost(parametros, url_select);
                lstArrayUsuarios = Usuario.getArrayListFromJSON(lstUsuarios);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                if (lstArrayUsuarios!=null && lstArrayUsuarios.size()>0){
                    onLoginUserListener.resolve(lstArrayUsuarios.get(0));
                }
            }else {
                onLoginUserListener.reject("Fallo:Login Usuario");
            }
        }
    }
}
