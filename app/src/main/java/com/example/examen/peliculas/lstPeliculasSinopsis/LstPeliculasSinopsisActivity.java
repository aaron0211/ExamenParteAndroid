package com.example.examen.peliculas.lstPeliculasSinopsis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculasGenero.LstPeliculasGeneroPresenter;

import java.util.ArrayList;

public class LstPeliculasSinopsisActivity extends AppCompatActivity implements LstPeliculasSinopsisContract.View{
    private RecyclerView recycler;
    private LstPeliculasSinopsisPresenter lstPeliculasSinopsisPresenter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_peliculas_sinopsis);

        Intent i = this.getIntent();
        String sinopsis = i.getStringExtra("SINOPSIS");

        lstPeliculasSinopsisPresenter = new LstPeliculasSinopsisPresenter(this);
        lstPeliculasSinopsisPresenter.getPeliculasSinopsis(sinopsis);
    }

    @Override
    public void success(ArrayList<Pelicula> peliculas) {
        recycler = findViewById(R.id.recicladorSinopsis);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        Adapter adapter = new Adapter(peliculas);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}