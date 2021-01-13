package com.example.examen.peliculas.lstPeliculasTOP;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasTopPresenter implements LstPeliculasTopContract.Presenter {
    private LstPeliculasTopModel lstPeliculasTopModel;
    private LstPeliculasTopContract.View view;

    public LstPeliculasTopPresenter(LstPeliculasTopContract.View view){
        this.view = view;
        lstPeliculasTopModel = new LstPeliculasTopModel();
    }

    @Override
    public void getPeliculasTop() {
        lstPeliculasTopModel.getPeliculasTopWS(new LstPeliculasTopContract.Model.OnLstPeliculasTopListener() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.success(peliculas);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        });
    }
}
