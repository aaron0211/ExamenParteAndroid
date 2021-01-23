package com.example.examen.peliculas.fdPelicula;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.adapter.AdapterSesion;
import com.example.examen.beans.Cine;
import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.fdPelicula.FdPeliculaContract;
import com.example.examen.peliculas.fdPelicula.FdPeliculaPresenter;
import com.example.examen.utils.Post;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class FdPeliculaActivity extends AppCompatActivity implements FdPeliculaContract.View {

    private TextView titulo,fecha,sinopsis;
    private ImageView imagen;
    private YouTubePlayerView youtube;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private FdPeliculaPresenter fdPeliculaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd_pelicula);

        titulo = findViewById(R.id.tvTitulo);
        fecha = findViewById(R.id.tvfecha);
        sinopsis = findViewById(R.id.tvSinopsis);
        sinopsis.setMovementMethod(new ScrollingMovementMethod());
        imagen = findViewById(R.id.fdImagen);
        youtube = findViewById(R.id.ytTrailer);
        getLifecycle().addObserver(youtube);

        Intent intent = getIntent();
        titulo.setText(intent.getStringExtra("titulo"));
        fecha.setText(intent.getStringExtra("fecha"));
        sinopsis.setText("Sinopsis: "+intent.getStringExtra("sinopsis"));
        Picasso.get().load(intent.getStringExtra("imagen")).into(imagen);

        fdPeliculaPresenter = new FdPeliculaPresenter(this);
        fdPeliculaPresenter.getSesiones(intent.getStringExtra("titulo"));

        youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = intent.getStringExtra("trailer");
                youTubePlayer.loadVideo(videoId,0);
            }
        });
    }

    @Override
    public void success(ArrayList<Pelicula> peliculas) {
        recycler = findViewById(R.id.recicladorFicha);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        AdapterSesion adapter = new AdapterSesion(peliculas);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}