package com.example.whalemusic.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.whalemusic.config.AppDataBase;
import com.example.whalemusic.dao.room.RoomDao;
import com.example.whalemusic.utils.Connectivity;
import com.example.whalemusic.utils.ResultListener;
import com.example.whalemusic.model.Artista;
import com.example.whalemusic.dao.retrofit.DatosDao;

import java.util.List;

public class ArtistaController {
    private DatosDao datosDao;
    private RoomDao roomDao;
    private Context context;
    Connectivity connectivity;

    public ArtistaController(Context context) {
        this.datosDao = new DatosDao();
        this.roomDao = AppDataBase.getInstance(context).roomDao();
        this.connectivity = new Connectivity();
        this.context = context;
    }


    public void traerArtistasChart(final ResultListener listenerDeLaVista){

        if(connectivity.hayInternet(context)) {
            datosDao.traerArtistas(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> result) {
                    List<Artista>listaArtista = result;
                    roomDao.borrarTodosLosArtistas();
                    roomDao.insertarListaArtistas(listaArtista);
                    listenerDeLaVista.finish(result);
                }
            });
        }else{
            List<Artista>todasLosArtistas = roomDao.seleccionarTodosLosArtistas();
            listenerDeLaVista.finish(todasLosArtistas);
        }

    }

    //KJ: 1.- Creé una método en el Controller de Artista que me permitirá conseguir un artista por una palabra ingresada
    //Es un copy/paste del método de arriba pero realizo un overloading ya que adicional pido por parámetro un String
    //que será lo que se ingresará en el EditText.
    public void buscarArtistas(String unaPalabra, final ResultListener listenerDeLaVista){
        datosDao.buscarArtista(unaPalabra, 10, new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

}
