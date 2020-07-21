package com.example.whalemusic.dao.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.model.Genero;

import java.util.List;

@Dao
public interface RoomDao {


    @Query("SELECT * FROM cancion")
    List<Cancion> seleccionarTodasLasCanciones();

    @Query("SELECT * FROM artista")
    List<Artista> seleccionarTodosLosArtistas();

    @Query("SELECT * FROM genero")
    List<Genero> seleccionarTodosLosGeneros();

    @Insert
    void insertarListaCanciones(List<Cancion> canciones);

    @Insert
    void insertarListaArtistas(List<Artista> artistas);

    @Insert
    void insertarListaGeneros(List<Genero> generos);

    @Query("DELETE FROM cancion")
    void borrarTodasLasCanciones();

    @Query("DELETE FROM artista")
    void borrarTodosLosArtistas();

    @Query("DELETE FROM genero")
    void borrarTodosLosGeneros();



}
