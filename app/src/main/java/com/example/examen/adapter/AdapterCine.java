package com.example.examen.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.R;
import com.example.examen.beans.Cine;
import com.example.examen.peliculas.lstPeliculasCine.LstPeliculasCineActivity;

import java.util.ArrayList;

public class AdapterCine extends RecyclerView.Adapter<AdapterCine.CineViewHolder> {
    ArrayList<Cine> lstCines;

    public AdapterCine (ArrayList<Cine> lstCines){
        this.lstCines = lstCines;
    }


    public static class CineViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre,localidad;

        public CineViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreCine);
            localidad = itemView.findViewById(R.id.localidad);
        }
    }

    @NonNull
    @Override
    public CineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cine_row,parent,false);
        return new CineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CineViewHolder holder, int position) {
        Cine cine = lstCines.get(position);
        holder.nombre.setText(cine.getNombre());
        holder.localidad.setText(cine.getLocalidad());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LstPeliculasCineActivity.class);
                i.putExtra("nombre",cine.getNombre());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstCines.size();
    }
}
