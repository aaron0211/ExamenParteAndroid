package com.example.examen.cines.lstCines;

import com.example.examen.beans.Cine;

import java.util.ArrayList;

public class LstCinesPresenter implements LstCinesContract.Presenter{
    private LstCinesModel lstCinesModel;
    private LstCinesContract.View vista;

    public LstCinesPresenter(LstCinesContract.View view){
        this.vista = view;
        lstCinesModel = new LstCinesModel();
    }

    @Override
    public void getCines() {
        lstCinesModel.getCinesWS(new LstCinesContract.Model.OnLstCinesListener() {
            @Override
            public void resolve(ArrayList<Cine> cines) {
                vista.success(cines);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        });
    }
}
