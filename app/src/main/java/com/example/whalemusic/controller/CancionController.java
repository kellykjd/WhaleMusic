package com.example.whalemusic.controller;

import android.content.Context;
import android.widget.Toast;
import com.example.whalemusic.config.AppDataBase;
import com.example.whalemusic.dao.room.RoomDao;
import com.example.whalemusic.utils.Connectivity;
import com.example.whalemusic.utils.ResultListener;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.dao.retrofit.DatosDao;
import java.util.List;

public class CancionController {

    private DatosDao datosDao;
    private RoomDao roomDao;
    private Context context;
    private Connectivity connectivity;

    public CancionController(Context context) {
        this.datosDao = new DatosDao();
        this.roomDao = AppDataBase.getInstance(context).roomDao();
        this.connectivity = new Connectivity();
        this.context = context;
    }


    public void traerCancionesChart(final ResultListener listenerDeLaVista) {

        if(connectivity.hayInternet(context)) {
            datosDao.traerCanciones(new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> result) {
                    List<Cancion>listaCancion = result;
                    roomDao.borrarTodasLasCanciones();
                    roomDao.insertarListaCanciones(listaCancion);
                    listenerDeLaVista.finish(result);
                }
            });
        }else{
            Toast.makeText(context,"No hay conexión a internet.",Toast.LENGTH_LONG).show();
            List<Cancion>todasLasCanciones = roomDao.seleccionarTodasLasCanciones();
            listenerDeLaVista.finish(todasLasCanciones);
        }
    }


    public void buscarCancion(String unaPalabra, final ResultListener listenerDeLaVista) {
        datosDao.buscarCancion(unaPalabra, 16, new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

    public void traerCancionesTopArtista(Integer idArtista, final ResultListener listenerDeLaVista) {
        datosDao.traerTopCancionesArtista(idArtista, new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

    public void traerCancionesAlbum(Integer idAlbum, final ResultListener listenerDeLaVista) {
        datosDao.traerCancionesAlbum(idAlbum, new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

    public void traerCancionesPlaylist(String idPlaylist, final ResultListener listenerDeLaVista) {
        datosDao.traerCancionesPlaylist(idPlaylist, new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

}

