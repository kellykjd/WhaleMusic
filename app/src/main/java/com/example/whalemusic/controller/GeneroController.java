package com.example.whalemusic.controller;

import android.content.Context;

import com.example.whalemusic.config.AppDataBase;
import com.example.whalemusic.dao.room.RoomDao;
import com.example.whalemusic.utils.Connectivity;
import com.example.whalemusic.utils.ResultListener;
import com.example.whalemusic.dao.retrofit.DatosDao;
import com.example.whalemusic.model.Genero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneroController {
    private DatosDao datosDao;
    private RoomDao roomDao;
    private Context context;
    private Connectivity connectivity;

    public GeneroController(Context context) {
        this.datosDao = new DatosDao();
        this.roomDao = AppDataBase.getInstance(context).roomDao();
        this.connectivity = new Connectivity();
        this.context = context;
    }

    public void traerGeneros(final ResultListener listenerDeLaVista){

        if(connectivity.hayInternet(context)) {
            datosDao.traerGeneros(new ResultListener<List<Genero>>() {
                @Override
                public void finish(List<Genero> result) {
                    result.remove(0);
                    List<Genero> generoList = new ArrayList<>();
                    Collections.shuffle(result);
                    for (int i = 0; i < 7; i++) {
                        generoList.add(result.get(i));
                    }
                    roomDao.borrarTodosLosGeneros();
                    roomDao.insertarListaGeneros(generoList);
                    listenerDeLaVista.finish(generoList);
                }
            });
        }else{
            List<Genero>todosLosGeneros = roomDao.seleccionarTodosLosGeneros();
            listenerDeLaVista.finish(todosLosGeneros);
        }
    }

}
