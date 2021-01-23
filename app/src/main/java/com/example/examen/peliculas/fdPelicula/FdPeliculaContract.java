package com.example.examen.peliculas.fdPelicula;

import com.example.examen.beans.Pelicula;
import com.example.examen.peliculas.lstPeliculasGenero.LstPeliculasGeneroContract;

import java.util.ArrayList;

public interface FdPeliculaContract {
    interface View{
        void success(ArrayList<Pelicula> peliculas);
        void error(String message);
    }
    interface Presenter{
        void getSesiones(String titulo);
    }
    interface Model{
        void getSesionesWS(OnFdPeliculasListener onFdPeliculasListener, String titulo);

        interface OnFdPeliculasListener{
            void resolve(ArrayList<Pelicula> peliculas);
            void reject(String error);
        }
    }
}
