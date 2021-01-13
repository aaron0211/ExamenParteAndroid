package com.example.examen.users.loginUser;

import com.example.examen.beans.Usuario;

public class LoginUserPresenter implements LoginUserContract.Presenter{
    private LoginUserContract.View vista;
    private LoginUserModel model;

    public LoginUserPresenter(LoginUserContract.View vista){
        this.vista = vista;
        this.model = new LoginUserModel();
    }

    @Override
    public void getUsuario(Usuario usuario) {
        model.getUsuarioWS(new LoginUserContract.Model.OnLoginUserListener() {
            @Override
            public void resolve(Usuario usuario) {
                vista.successLogin(usuario);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        }, usuario);
    }
}
