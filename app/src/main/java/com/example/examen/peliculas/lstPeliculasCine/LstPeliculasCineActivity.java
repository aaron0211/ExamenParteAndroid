package com.example.examen.peliculas.lstPeliculasCine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.adapter.AdapterPeliCine;
import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasCineActivity extends AppCompatActivity implements LstPeliculasCineContract.View{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private LstPeliculasCinePresenter lstPeliculasCinePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_peliculas_cine);

        Intent i = this.getIntent();
        Bundle extra = i.getExtras();
        String nombre = extra.getString("nombre");

        lstPeliculasCinePresenter = new LstPeliculasCinePresenter(this);
        lstPeliculasCinePresenter.getPeliculas(nombre);
    }

    @Override
    public void success(ArrayList<Pelicula> lstPeliculas) {
        recyclerView = findViewById(R.id.recicladorPorCine);
        recyclerView.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lManager);
        AdapterPeliCine adapter = new AdapterPeliCine(lstPeliculas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}