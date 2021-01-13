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


public class AdapterPeliCine extends RecyclerView.Adapter<AdapterPeliCine.PeliCineViewHolder> {
    ArrayList<Pelicula> lstPeliculas;

    public AdapterPeliCine(ArrayList<Pelicula> lstPeliculas){
        this.lstPeliculas = lstPeliculas;
    }

    public static class PeliCineViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagenPeli;
        public TextView nombrePeli, fecha, hora;
        public RatingBar ratingBarPeli;

        public PeliCineViewHolder(View v){
            super(v);
            imagenPeli = v.findViewById(R.id.imagenPeli);
            nombrePeli = v.findViewById(R.id.nombrePeli);
            fecha = v.findViewById(R.id.fecha);
            hora = v.findViewById(R.id.hora);
            ratingBarPeli = v.findViewById(R.id.rbRatingPeli);
        }
    }

    @NonNull
    @Override
    public PeliCineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelicula_cine_row,parent,false);
        return new PeliCineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliCineViewHolder holder, int position) {
        Pelicula pelicula = lstPeliculas.get(position);
        float estrellas = (pelicula.getS_puntuacion()/(float)pelicula.getN_votos());

        holder.nombrePeli.setText(pelicula.getTitulo());
        holder.fecha.setText(pelicula.getFecha());
        holder.hora.setText(pelicula.getHora());
        holder.ratingBarPeli.setRating(estrellas);
        Picasso.get().load(pelicula.getUrl())
                .into(holder.imagenPeli);

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
