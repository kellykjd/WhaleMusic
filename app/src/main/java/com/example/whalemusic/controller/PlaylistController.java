package com.example.whalemusic.controller;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.whalemusic.dao.retrofit.DatosDao;
import com.example.whalemusic.model.Playlist;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

public class PlaylistController {
    private DatosDao datosDao;

    public PlaylistController(Context context){this.datosDao = new DatosDao();
    }

    public void traerPlaylist(String unaPalabra, final ResultListener listenerDeLaVista) {
        datosDao.traerPlaylist(unaPalabra, 10, new ResultListener<List<Playlist>>() {
            @Override
            public void finish(List<Playlist> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }

}
