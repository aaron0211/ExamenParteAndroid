package com.example.examen.cines.lstCines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.adapter.AdapterCine;
import com.example.examen.beans.Cine;
import com.example.examen.peliculas.lstPeliculas.view.LstPeliculasView;
import com.example.examen.peliculas.lstPeliculasTOP.LstPeliculasTopActivity;

import java.util.ArrayList;

public class LstCinesActivity extends AppCompatActivity implements LstCinesContract.View{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private LstCinesPresenter lstCinesPresenter;
    private Button btPeliculas, btTOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_cines);

        lstCinesPresenter = new LstCinesPresenter(this);
        lstCinesPresenter.getCines();

        btPeliculas = findViewById(R.id.btAll);
        btTOP = findViewById(R.id.btTOP10);
        btPeliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LstPeliculasView.class);
                v.getContext().startActivity(i);
            }
        });
        btTOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LstPeliculasTopActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public void success(ArrayList<Cine> cines) {
        recyclerView = findViewById(R.id.recyclerCines);
        recyclerView.setHasFixedSize(true);

        lManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(lManager);
        AdapterCine adapter = new AdapterCine(cines);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}