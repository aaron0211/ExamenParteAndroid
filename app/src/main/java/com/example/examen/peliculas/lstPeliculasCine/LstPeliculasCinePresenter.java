package com.example.examen.peliculas.lstPeliculasCine;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasCinePresenter implements LstPeliculasCineContract.Presenter{
    private LstPeliculasCineModel lstPeliculasCineModel;
    private LstPeliculasCineContract.View vista;

    public LstPeliculasCinePresenter(LstPeliculasCineContract.View vista){
        this.vista = vista;
        lstPeliculasCineModel = new LstPeliculasCineModel();
    }

    @Override
    public void getPeliculas(String nombre) {
        lstPeliculasCineModel.getPeliculasWS(new LstPeliculasCineContract.Model.OnLstPeliculasCineListener() {
            @Override
            public void resolve(ArrayList<Pelicula> lstPeliculas) {
                vista.success(lstPeliculas);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        },nombre);
    }
}
