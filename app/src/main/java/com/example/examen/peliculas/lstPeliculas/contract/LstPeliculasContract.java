package com.example.examen.peliculas.lstPeliculas.contract;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public interface LstPeliculasContract {
    interface View{
        void success(ArrayList<Pelicula> peliculas);
        void error(String message);
    }
    interface Presenter{
        void getPeliculas();
    }
    interface Model{
        void getPeliculasWS(OnLstPeliculasListener onLstPeliculasListener);

        interface OnLstPeliculasListener{
            void resolve(ArrayList<Pelicula> peliculas);
            void reject(String error);
        }
    }
}
