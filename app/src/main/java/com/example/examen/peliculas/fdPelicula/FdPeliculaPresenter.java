package com.example.examen.peliculas.fdPelicula;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public class FdPeliculaPresenter  implements FdPeliculaContract.Presenter{
    private FdPeliculaModel fdPeliculaModel;
    private FdPeliculaContract.View view;

    public FdPeliculaPresenter(FdPeliculaContract.View view){
        this.view = view;
        fdPeliculaModel = new FdPeliculaModel();
    }

    @Override
    public void getPeliculas(String titulo) {
        fdPeliculaModel.getPeliculasWS(new FdPeliculaContract.Model.OnFdPeliculasListener() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.success(peliculas);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        },titulo);
    }
}
