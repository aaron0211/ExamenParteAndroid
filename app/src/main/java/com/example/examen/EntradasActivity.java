package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EntradasActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView cine,titulo,fecha,hora,precio, total;
    private EditText cantidad;
    private Button btAplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);

        initComponent();

        Intent intent = this.getIntent();
        cine.setText(intent.getStringExtra("cine"));
        titulo.setText(intent.getStringExtra("titulo"));
        fecha.setText(intent.getStringExtra("fecha"));
        hora.setText(intent.getStringExtra("hora"));
        precio.setText("Precio "+intent.getStringExtra("precio")+"€");
        total.setText("Total "+intent.getStringExtra("precio")+"€");
        Picasso.get().load(intent.getStringExtra("url")).into(imagen);

        btAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(cantidad.getText().toString())>=1) {
                    float resultado = Float.parseFloat(intent.getStringExtra("precio")) * Integer.parseInt(cantidad.getText().toString());
                    total.setText("Total "+resultado+"€");
                    cantidad.setText("");
                    cantidad.setHint("Nº Entradas");
                }else {
                    total.setText(precio.getText());
                }
            }
        });
    }

    public void initComponent(){
        imagen = findViewById(R.id.resumenImagen);
        cine = findViewById(R.id.resumenCine);
        titulo = findViewById(R.id.resumenTitulo);
        fecha = findViewById(R.id.resumenFecha);
        hora = findViewById(R.id.resumeHora);
        precio = findViewById(R.id.resumenPrecio);
        total = findViewById(R.id.total);
        cantidad = findViewById(R.id.cantidad);
        btAplicar = findViewById(R.id.btAplicar);
    }
}