package com.example.examen.peliculas.lstPeliculas.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examen.R;
import com.example.examen.adapter.Adapter;
import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculas.contract.LstPeliculasContract;
import com.example.examen.peliculas.lstPeliculas.presenter.LstPeliculasPresenter;
import com.example.examen.peliculas.lstPeliculasGenero.LstPeliculasGeneroActivity;
import com.example.examen.peliculas.lstPeliculasSinopsis.LstPeliculasSinopsisActivity;

import java.util.ArrayList;

public class LstPeliculasView extends AppCompatActivity implements LstPeliculasContract.View {

    private RecyclerView recycler;
    private LstPeliculasPresenter lstPeliculasPresenter;
    private RecyclerView.LayoutManager lManager;
    private Spinner spinner;
    private String[] listaSpinner = {"Género", "Accion", "Aventuras", "Comedia", "Drama", "Terror", "Musical", "Ficcion", "Belica", "Suspense", "Romantica", "Animacion"};
    private Button buscar;
    private EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstPeliculasPresenter = new LstPeliculasPresenter(this);
        lstPeliculasPresenter.getPeliculas();

        buscar = findViewById(R.id.btBuscar);
        etBuscar = findViewById(R.id.etBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sinopsis = etBuscar.getText().toString();
                Intent intent = new Intent(getBaseContext(), LstPeliculasSinopsisActivity.class);
                intent.putExtra("SINOPSIS", sinopsis);
                startActivity(intent);
            }
        });
        cargarCombo();
    }

    @Override
    public void success(ArrayList<Pelicula> peliculas) {
        recycler = findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        Adapter adapter = new Adapter(peliculas);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void cargarCombo(){
        spinner = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaSpinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelected(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String genero = parent.getItemAtPosition(position).toString();
                if (genero == "Género")
                    return;
                Intent intent = new Intent(getBaseContext(), LstPeliculasGeneroActivity.class);
                intent.putExtra("GENERO", genero);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}