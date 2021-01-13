package com.example.examen.cines.lstCines;

import com.example.examen.beans.Cine;

import java.util.ArrayList;

public interface LstCinesContract {
    interface View{
        void success(ArrayList<Cine> cines);
        void error(String message);
    }
    interface Presenter{
        void getCines();
    }
    interface Model{
        void getCinesWS(LstCinesContract.Model.OnLstCinesListener onLstCinesListener);

        interface OnLstCinesListener{
            void resolve(ArrayList<Cine> cines);
            void reject(String error);
        }
    }
}
