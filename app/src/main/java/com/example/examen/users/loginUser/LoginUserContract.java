package com.example.examen.users.loginUser;

import com.example.examen.beans.Usuario;

public interface LoginUserContract {
    interface View{
        void successLogin(Usuario usuario);
        void error(String message);
    }
    interface Presenter{
        void getUsuario(Usuario usuario);
    }
    interface Model{
        void getUsuarioWS(OnLoginUserListener onLoginUserListener, Usuario usuario);
        interface OnLoginUserListener{
            void resolve(Usuario usuario);
            void reject(String error);
        }
    }
}
