package com.example.whalemusic.dao.retrofit;

import android.util.Log;

import com.example.whalemusic.model.Album;
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
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosDao extends DatosRetrofitDAO {
    public static final String TAG = "DatosDao";
    public static final String BASE_URL = "https://api.deezer.com/";

    public DatosDao() {
        super(BASE_URL);
    }

    public void traerArtistas(final ResultListener<List<Artista>> listenerDelCotroller) {
        Call<Chart> call = apiService.traerChart();

        call.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {
                Chart containerArtista = response.body();
                listenerDelCotroller.finish(containerArtista.getArtists().getData());
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {
                Log.d(TAG, "Falló el pedido traerArtistas");
            }
        });


    }

    //KJ: 3.- En el DAO creo el método que llamará el GET de la Interfaz y me va a traer el container de Artistas
    public void buscarArtista(String busqueda, Integer limite, final ResultListener<List<Artista>> listenerDelController) {
        Call<ContainerArtista> call = apiService.buscarArtistaPorPalabra(busqueda, limite);

        call.enqueue(new Callback<ContainerArtista>() {
            @Override
            public void onResponse(Call<ContainerArtista> call, Response<ContainerArtista> response) {
                ContainerArtista containerArtista = response.body();
                listenerDelController.finish(containerArtista.getData());
            }

            @Override
            public void onFailure(Call<ContainerArtista> call, Throwable t) {
                Log.d(TAG, "Falló el pedido buscarArtista");
            }
        });
    }

    public void traerPlaylist(String busqueda, Integer limite, final ResultListener<List<Playlist>> listenerDelController) {
        Call<ContainerPlaylist> call = apiService.traerPlaylist(busqueda, limite);

        call.enqueue(new Callback<ContainerPlaylist>() {
            @Override
            public void onResponse(Call<ContainerPlaylist> call, Response<ContainerPlaylist> response) {
                ContainerPlaylist containerPlaylist = response.body();
                listenerDelController.finish(containerPlaylist.getData());
            }

            @Override
            public void onFailure(Call<ContainerPlaylist> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Falló el pedido traerPlaylist");
            }
        });
    }
    public void traerCanciones(final ResultListener<List<Cancion>> listenerDelCotroller) {
        Call<Chart> call = apiService.traerChart();

        call.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {
                Chart containerCancion = response.body();
                listenerDelCotroller.finish(containerCancion.getTracks().getData());
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {
                Log.d(TAG, "Falló el pedido traerCanciones");
            }
        });


    }

    public void buscarCancion(String busqueda, Integer limite, final ResultListener<List<Cancion>> listenerDelController) {
        Call<ContainerCancion> call = apiService.buscarCancionPorPalabra(busqueda, limite);

        call.enqueue(new Callback<ContainerCancion>() {
            @Override
            public void onResponse(Call<ContainerCancion> call, Response<ContainerCancion> response) {
                ContainerCancion containerCancion = response.body();
                listenerDelController.finish(containerCancion.getData());
            }

            @Override
            public void onFailure(Call<ContainerCancion> call, Throwable t) {
                Log.d(TAG, "Falló el pedido buscarCancion");
            }
        });
    }

    public void traerGeneros(final ResultListener<List<Genero>> listenerDelController) {
        Call<ContainerGenero> call = apiService.traerGeneros();

        call.enqueue(new Callback<ContainerGenero>() {
            @Override
            public void onResponse(Call<ContainerGenero> call, Response<ContainerGenero> response) {
                ContainerGenero containerGenero = response.body();
                listenerDelController.finish(containerGenero.getData());
            }

            @Override
            public void onFailure(Call<ContainerGenero> call, Throwable t) {
                Log.d(TAG, "Falló el pedido traerGeneros");
            }
        });
    }

    public void buscarAlbum(String busqueda, Integer limite, final ResultListener<List<Album>> listenerDelController) {
        Call<ContainerAlbum> call = apiService.buscarAlbumPorPalabra(busqueda, limite);

        call.enqueue(new Callback<ContainerAlbum>() {
            @Override
            public void onResponse(Call<ContainerAlbum> call, Response<ContainerAlbum> response) {
                ContainerAlbum containerAlbum = response.body();
                listenerDelController.finish(containerAlbum.getData());
            }

            @Override
            public void onFailure(Call<ContainerAlbum> call, Throwable t) {
                Log.d(TAG, "Falló el pedido buscarAlbum");
            }
        });
    }

    public void traerTopCancionesArtista(Integer idArtista, final ResultListener<List<Cancion>> listenerDelController) {
        Call<ContainerCancion> call = apiService.traerTopCanciones(idArtista);

        call.enqueue(new Callback<ContainerCancion>() {
            @Override
            public void onResponse(Call<ContainerCancion> call, Response<ContainerCancion> response) {
                ContainerCancion containerCancion = response.body();
                listenerDelController.finish(containerCancion.getData());
            }

            @Override
            public void onFailure(Call<ContainerCancion> call, Throwable t) {
                Log.d(TAG, "Falló el pedido traerTopCancionesArtista");
            }
        });
    }
    public void traerCancionesAlbum(Integer idAlbum, final ResultListener<List<Cancion>> listenerDelController) {
        Call<ContainerCancion> call = apiService.traerCancionesAlbum(idAlbum);

        call.enqueue(new Callback<ContainerCancion>() {
            @Override
            public void onResponse(Call<ContainerCancion> call, Response<ContainerCancion> response) {
                ContainerCancion containerCancion = response.body();
                listenerDelController.finish(containerCancion.getData());
            }

            @Override
            public void onFailure(Call<ContainerCancion> call, Throwable t) {
                Log.d(TAG, "Falló el pedido traerCancionesAlbum");
            }
        });
    }

    public void traerCancionesPlaylist(String idPlaylist, final ResultListener<List<Cancion>> listenerDelController) {
        Call<ContainerCancion> call = apiService.traerCancionesPlaylist(idPlaylist);

        call.enqueue(new Callback<ContainerCancion>() {
            @Override
            public void onResponse(Call<ContainerCancion> call, Response<ContainerCancion> response) {
                ContainerCancion containerCancion = response.body();
                listenerDelController.finish(containerCancion.getData());
            }

            @Override
            public void onFailure(Call<ContainerCancion> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Falló el pedido traerCancionesPlaylist");
            }
        });
    }

}

