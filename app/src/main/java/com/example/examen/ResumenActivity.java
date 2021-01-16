package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ResumenActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView cine,titulo,fecha,hora,precio;
    private int cantidad;
    private String precioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        initComponents();

        Intent intent = this.getIntent();
        cine.setText(intent.getStringExtra("cine"));
        titulo.setText(intent.getStringExtra("titulo"));
        fecha.setText(intent.getStringExtra("fecha"));
        hora.setText(intent.getStringExtra("hora"));
        cantidad = intent.getIntExtra("cantidad",1);
        precioTotal = String.valueOf(intent.getFloatExtra("total",1));
        precio.setText(cantidad+" x "+intent.getStringExtra("precio")+"€ = "+precioTotal+"€");
        Picasso.get().load(intent.getStringExtra("url")).into(imagen);
    }

    public void initComponents(){
        imagen = findViewById(R.id.resumenImagen);
        cine = findViewById(R.id.resumenCine);
        titulo = findViewById(R.id.resumenTitulo);
        fecha = findViewById(R.id.resumenFecha);
        hora = findViewById(R.id.resumeHora);
        precio = findViewById(R.id.resumenPrecio);
    }
}