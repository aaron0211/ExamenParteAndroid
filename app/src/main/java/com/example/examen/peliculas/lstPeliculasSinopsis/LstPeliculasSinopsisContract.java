package com.example.examen.peliculas.lstPeliculasSinopsis;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public interface LstPeliculasSinopsisContract {
    interface View{
        void success(ArrayList<Pelicula> peliculas);
        void error(String message);
    }
    interface Presenter{
        void getPeliculasSinopsis(String sinopsis);
    }
    interface Model{
        void getPeliculasSinopsisWS(OnLstPeliculasSinopsisListener onLstPeliculasSinopsisListener, String sinopsis);

        interface OnLstPeliculasSinopsisListener{
            void resolve(ArrayList<Pelicula> peliculas);
            void reject(String error);
        }
    }
}
