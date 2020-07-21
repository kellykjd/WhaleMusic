package com.example.whalemusic.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.whalemusic.model.Album;
import com.example.whalemusic.dao.retrofit.DatosDao;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

public class AlbumController {
    public void buscarAlbum(String unaPalabra, final ResultListener listenerDeLaVista){
        DatosDao datosDao = new DatosDao();
        datosDao.buscarAlbum(unaPalabra, 10, new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

}
