package com.example.whalemusic.controller;

import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.dao.retrofit.FirestoreDao;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

public class FirestoreController {

    private FirestoreDao firestoreDao;

    public FirestoreController(){
        firestoreDao = new FirestoreDao();
    }

    public void agregarCancionAFavoritos (Cancion cancion){
        firestoreDao.agregarCancionAFavoritos(cancion);
    }


    public void traerListaDeFavorito(final ResultListener<List<Cancion>>listenerDeLaVista){
        firestoreDao.traerCancionesFavoritas(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> results) {
                listenerDeLaVista.finish(results);
            }
        });
    }
}