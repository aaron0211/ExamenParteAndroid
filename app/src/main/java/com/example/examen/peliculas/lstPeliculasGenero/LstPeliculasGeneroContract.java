package com.example.examen.peliculas.lstPeliculasGenero;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public interface LstPeliculasGeneroContract {
    interface View{
        void success(ArrayList<Pelicula> peliculas);
        void error(String message);
    }
    interface Presenter{
        void getPeliculasGenero(String genero);
    }
    interface Model{
        void getPeliculasGeneroWS(OnLstPeliculasGeneroListener onLstPeliculasGeneroListener, String genero);

        interface OnLstPeliculasGeneroListener{
            void resolve(ArrayList<Pelicula> peliculas);
            void reject(String error);
        }
    }
}
