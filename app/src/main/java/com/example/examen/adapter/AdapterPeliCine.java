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

import com.example.examen.EntradasActivity;
import com.example.examen.peliculas.fdPelicula.FdPeliculaActivity;
import com.example.examen.R;
import com.example.examen.beans.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterPeliCine extends RecyclerView.Adapter<AdapterPeliCine.PeliCineViewHolder> {
    ArrayList<Pelicula> lstPeliculas;
    private String nombre,localidad;

    public AdapterPeliCine(ArrayList<Pelicula> lstPeliculas, String nombre, String localidad){
        this.lstPeliculas = lstPeliculas;
        this.nombre = nombre;
        this.localidad = localidad;
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
                Intent i = new Intent(v.getContext(), EntradasActivity.class);
                i.putExtra("titulo",pelicula.getTitulo());
                i.putExtra("cine",nombre+"("+localidad+")");
                i.putExtra("fecha",pelicula.getF_estreno());
                i.putExtra("hora",pelicula.getHora());
                i.putExtra("precio",pelicula.getPrecio());
                i.putExtra("url",pelicula.getUrl());
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstPeliculas.size();
    }
}
