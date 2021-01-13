package com.example.examen.peliculas.lstPeliculasGenero;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasGeneroPresenter implements LstPeliculasGeneroContract.Presenter{

    private LstPeliculasGeneroModel lstPeliculasGeneroModel;
    private LstPeliculasGeneroContract.View view;

    public LstPeliculasGeneroPresenter(LstPeliculasGeneroContract.View view){
        this.view = view;
        lstPeliculasGeneroModel = new LstPeliculasGeneroModel();
    }

    @Override
    public void getPeliculasGenero(String genero) {
        lstPeliculasGeneroModel.getPeliculasGeneroWS(new LstPeliculasGeneroContract.Model.OnLstPeliculasGeneroListener() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.success(peliculas);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        },genero);
    }
}
