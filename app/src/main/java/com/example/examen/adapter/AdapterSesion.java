package com.example.examen.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.EntradasActivity;
import com.example.examen.R;
import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculasCine.LstPeliculasCineActivity;

import java.util.ArrayList;

public class AdapterSesion extends RecyclerView.Adapter<AdapterSesion.SesionViewHolder>{
    private ArrayList<Pelicula> lstPeliculas;

    public AdapterSesion(ArrayList<Pelicula> lstPeliculas){
        this.lstPeliculas = lstPeliculas;
    }

    public static class SesionViewHolder extends RecyclerView.ViewHolder{
        private TextView cine,fecha,hora;
        private Button seleccionar;

        public SesionViewHolder(@NonNull View itemView) {
            super(itemView);
            cine = itemView.findViewById(R.id.cine);
            fecha = itemView.findViewById(R.id.sesionFecha);
            hora = itemView.findViewById(R.id.sesionHora);
            seleccionar = itemView.findViewById(R.id.btSesion);
        }
    }

    @NonNull
    @Override
    public SesionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sesion,parent,false);
        return new SesionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SesionViewHolder holder, int position) {
        Pelicula pelicula = lstPeliculas.get(position);
        holder.cine.setText(pelicula.getCine()+" ("+pelicula.getLocalidad()+")");
        holder.fecha.setText(pelicula.getFecha());
        holder.hora.setText(pelicula.getHora());

        holder.seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), EntradasActivity.class);
                i.putExtra("titulo",pelicula.getTitulo());
                i.putExtra("cine",pelicula.getCine()+"("+pelicula.getLocalidad()+")");
                i.putExtra("fecha",pelicula.getFecha());
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
