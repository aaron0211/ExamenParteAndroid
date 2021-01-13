package com.example.examen.peliculas.lstPeliculasCine;

import com.example.examen.beans.Pelicula;

import java.util.ArrayList;

public interface LstPeliculasCineContract {
    interface View{
        void success(ArrayList<Pelicula> lstPeliculas);
        void error(String message);
    }
    interface Presenter{
        void getPeliculas(String nombre);
    }
    interface Model{
        void getPeliculasWS(LstPeliculasCineContract.Model.OnLstPeliculasCineListener onLstPeliculasCineListener, String nombre);
        interface OnLstPeliculasCineListener{
            void resolve(ArrayList<Pelicula> lstPeliculas);
            void reject(String error);
        }
    }
}
