package com.example.whalemusic.utils;

import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.model.Chart;
import com.example.whalemusic.model.ContainerAlbum;
import com.example.whalemusic.model.ContainerArtista;
import com.example.whalemusic.model.ContainerCancion;
import com.example.whalemusic.model.ContainerGenero;
import com.example.whalemusic.model.ContainerPlaylist;
import com.example.whalemusic.model.Genero;
import com.example.whalemusic.model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("chart/0")
    Call<Chart> traerChart();

    @GET ("/genre")
    Call<ContainerGenero> traerGeneros();

    @GET("/artist/{id}")
    Call<Artista> traerArtista(@Path("id") int idArtista);

    @GET("/track/{id}")
    Call<Cancion> traerCancion(@Path("id") int idCancion);

    @GET("/artist/{id}/top?limit=50")
    Call<ContainerCancion> traerTopCanciones(@Path("id") Integer idArtista);

    //KJ: 2.- Agrego el query para buscar por palabra. En este caso buscaré por artista ingresado
    //(@Query("q") String unaPalabra) esto formatea la búsqueda de esta manera: /search/artist?q=unaPalabra
    @GET ("/search/artist")
    Call<ContainerArtista> buscarArtistaPorPalabra(@Query("q") String unaPalabra, @Query("limit") Integer limite);

    @GET("search/playlist")
    Call<ContainerPlaylist> traerPlaylist(@Query("q") String genero, @Query ("limit") Integer limite);

    @GET ("/search/track")
    Call<ContainerCancion> buscarCancionPorPalabra(@Query("q") String unaPalabra, @Query("limit") Integer limite);

    @GET ("/search/album")
    Call<ContainerAlbum> buscarAlbumPorPalabra(@Query("q")String unaPalabra, @Query("limit") Integer limite);

    @GET("/album/{id}/tracks")
    Call<ContainerCancion> traerCancionesAlbum(@Path("id") Integer idAlbum);

    @GET("/playlist/{id}/tracks")
    Call<ContainerCancion> traerCancionesPlaylist(@Path("id") String idPlaylist);


}
