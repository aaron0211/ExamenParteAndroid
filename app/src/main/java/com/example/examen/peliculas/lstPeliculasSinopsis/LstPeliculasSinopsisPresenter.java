package com.example.examen.peliculas.lstPeliculasSinopsis;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasSinopsisPresenter implements LstPeliculasSinopsisContract.Presenter{
    private LstPeliculasSinopsisModel lstPeliculasSinopsisModel;
    private LstPeliculasSinopsisContract.View view;

    public LstPeliculasSinopsisPresenter(LstPeliculasSinopsisContract.View view){
        this.view = view;
        lstPeliculasSinopsisModel = new LstPeliculasSinopsisModel();
    }

    @Override
    public void getPeliculasSinopsis(String sinopsis) {
        lstPeliculasSinopsisModel.getPeliculasSinopsisWS(new LstPeliculasSinopsisContract.Model.OnLstPeliculasSinopsisListener() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.success(peliculas);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        },sinopsis);
    }
}
