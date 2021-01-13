package com.example.examen.peliculas.lstPeliculasGenero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculas.presenter.LstPeliculasPresenter;

import java.util.ArrayList;

public class LstPeliculasGeneroActivity extends AppCompatActivity implements LstPeliculasGeneroContract.View{
    private RecyclerView recycler;
    private LstPeliculasGeneroPresenter lstPeliculasGeneroPresenter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_peliculas_genero);

        Intent i = this.getIntent();
        String genero = i.getStringExtra("GENERO");

        lstPeliculasGeneroPresenter = new LstPeliculasGeneroPresenter(this);
        lstPeliculasGeneroPresenter.getPeliculasGenero(genero);
    }

    @Override
    public void success(ArrayList<Pelicula> peliculas) {
        recycler = findViewById(R.id.recicladorGenero);
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