package com.example.examen.peliculas.lstPeliculas.presenter;

import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculas.contract.LstPeliculasContract;
import com.example.examen.peliculas.lstPeliculas.model.LstPeliculasModel;

import java.util.ArrayList;

public class LstPeliculasPresenter implements LstPeliculasContract.Presenter{

    private LstPeliculasModel lstPeliculasModel;
    private LstPeliculasContract.View vista;

    public LstPeliculasPresenter(LstPeliculasContract.View vista){
        this.vista = vista;
        this.lstPeliculasModel = new LstPeliculasModel();
    }

    @Override
    public void getPeliculas() {
        lstPeliculasModel.getPeliculasWS(new LstPeliculasContract.Model.OnLstPeliculasListener() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                vista.success(peliculas);
            }

            @Override
            public void reject(String error) {
                vista.error("Fallo al taer los datos. ");
            }
        });
    }
}
