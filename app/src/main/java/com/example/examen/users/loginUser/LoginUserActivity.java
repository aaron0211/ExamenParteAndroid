package com.example.examen.users.loginUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.beans.Usuario;
import com.example.examen.cines.lstCines.LstCinesActivity;
import com.example.examen.peliculas.lstPeliculas.view.LstPeliculasView;

public class LoginUserActivity extends AppCompatActivity implements LoginUserContract.View{
    private EditText email,pass;
    private Button login;
    private LoginUserPresenter loginUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        loginUserPresenter = new LoginUserPresenter(this);

        initComponents();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setPassword(pass.getText().toString());
                loginUserPresenter.getUsuario(usuario);
            }
        });
    }

    private void initComponents(){
        email = findViewById(R.id.etEmail);
        pass = findViewById(R.id.etPass);
        login = findViewById(R.id.btLogin);
    }

    @Override
    public void successLogin(Usuario usuario) {
        Toast.makeText(this,"Bienvenido "+usuario.getNombre(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), LstCinesActivity.class);
        startActivity(intent);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}