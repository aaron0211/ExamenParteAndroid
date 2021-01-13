package com.example.examen.peliculas.lstPeliculasTOP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculasSinopsis.LstPeliculasSinopsisPresenter;

import java.util.ArrayList;

public class LstPeliculasTopActivity extends AppCompatActivity implements LstPeliculasTopContract.View{
    private RecyclerView recycler;
    private LstPeliculasTopPresenter lstPeliculasTopPresenter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_peliculas_top);

        lstPeliculasTopPresenter = new LstPeliculasTopPresenter(this);
        lstPeliculasTopPresenter.getPeliculasTop();
    }

    @Override
    public void success(ArrayList<Pelicula> peliculas) {
        recycler = findViewById(R.id.recicladorTOP);
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