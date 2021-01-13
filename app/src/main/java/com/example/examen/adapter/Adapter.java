package com.example.examen.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.peliculas.fdPelicula.FdPeliculaActivity;
import com.example.examen.R;
import com.example.examen.beans.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.PeliculaViewHolder>{
    private ArrayList<Pelicula> lstPeliculas;

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;
        public RatingBar ratingBar;

        public PeliculaViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.imagen);
            nombre = v.findViewById(R.id.nombre);
            ratingBar = v.findViewById(R.id.rbRating);
        }
    }

    public Adapter(ArrayList<Pelicula> lstPeliculas){
        this.lstPeliculas = lstPeliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelicula_row,parent,false);
        return new PeliculaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = lstPeliculas.get(position);
        float estrellas = (pelicula.getS_puntuacion()/(float)pelicula.getN_votos());

        holder.nombre.setText(pelicula.getTitulo());
        holder.ratingBar.setRating(estrellas);
        Picasso.get().load(pelicula.getUrl())
                .into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FdPeliculaActivity.class);
                i.putExtra("titulo",pelicula.getTitulo());
                i.putExtra("fecha",pelicula.getF_estreno());
                i.putExtra("sinopsis",pelicula.getSinopsis());
                i.putExtra("trailer",pelicula.getTrailer());
                i.putExtra("imagen",pelicula.getUrl());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstPeliculas.size();
    }
}
