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
    private Button btAplicar,btConfirmar;
    private int num = 1;
    private float precioTotal;

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
        precioTotal=Float.parseFloat(intent.getStringExtra("precio"));

        btAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(cantidad.getText().toString())>=1) {
                    num = Integer.parseInt(String.valueOf(cantidad.getText()));
                    float resultado = Float.parseFloat(intent.getStringExtra("precio")) * Integer.parseInt(cantidad.getText().toString());
                    precioTotal = resultado;
                    total.setText("Total "+resultado+"€");
                    cantidad.setText("");
                    cantidad.setHint("Nº Entradas");
                }else {
                    total.setText(precio.getText());
                }
            }
        });
        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ResumenActivity.class);
                i.putExtra("titulo",titulo.getText());
                i.putExtra("cine",cine.getText());
                i.putExtra("fecha",fecha.getText());
                i.putExtra("hora",hora.getText());
                i.putExtra("precio",intent.getStringExtra("precio"));
                i.putExtra("total",precioTotal);
                i.putExtra("cantidad",num);
                i.putExtra("url",intent.getStringExtra("url"));
                v.getContext().startActivity(i);
            }
        });
    }

    public void initComponent(){
        imagen = findViewById(R.id.entradasImagen);
        cine = findViewById(R.id.entradasCine);
        titulo = findViewById(R.id.entradasTitulo);
        fecha = findViewById(R.id.entradasFecha);
        hora = findViewById(R.id.entradasHora);
        precio = findViewById(R.id.entradasPrecio);
        total = findViewById(R.id.total);
        cantidad = findViewById(R.id.cantidad);
        btAplicar = findViewById(R.id.btAplicar);
        btConfirmar = findViewById(R.id.btConfirmar);
    }
}