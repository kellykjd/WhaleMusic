package com.example.whalemusic.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.whalemusic.R;
import com.example.whalemusic.controller.AlbumController;
import com.example.whalemusic.controller.ArtistaController;
import com.example.whalemusic.controller.CancionController;
import com.example.whalemusic.model.Album;
import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResultados extends Fragment implements AdapterArtista.ListenerDelAdapterArtista, AdapterCancion.ListenerDelAdapterCancion, AdapterAlbum.ListenerDelAdapterAlbum{
    private TextView textViewArtistas, textViewCanciones, textViewAlbumes;
    private RecyclerView recyclerViewArtistas;
    private RecyclerView recyclerViewCanciones;
    private RecyclerView recyclerViewAlbumes;
    private FragmentResultados.ListenerDeFragment listenerDeFragment;
    private SearchView searchView;
    public static final String CLAVE_PALABRA = "unaPalabra";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resultados, container, false);
        encontrarVistas(view);
        Bundle bundle = getArguments();
        searchView.setFocusable(true);
        searchView.setIconified(false);
        try {
            if (!bundle.isEmpty()) {
                String palabraBuscada = bundle.getString(CLAVE_PALABRA);
                searchView.setQuery(palabraBuscada, false);
                searchView.clearFocus();
                mostrarBusqueda(palabraBuscada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                mostrarBusqueda(searchView.getQuery().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return view;
    }

    private void encontrarVistas(View view) {
        textViewArtistas = view.findViewById(R.id.fragmentResultados_TextView_artistas);
        textViewCanciones = view.findViewById(R.id.fragmentResultados_TextView_canciones);
        textViewAlbumes=view.findViewById(R.id.fragmentResultados_TextView_albumes);
        recyclerViewArtistas = view.findViewById(R.id.fragmentResultados_recyclerViewArtistas);
        recyclerViewCanciones = view.findViewById(R.id.fragmentResultados_recyclerViewCanciones);
        recyclerViewAlbumes = view.findViewById(R.id.fragmentResultados_recyclerViewAlbumes);
        searchView = view.findViewById(R.id.fragmentResultados_EditText_searchView);
    }

    private void mostrarBusqueda(String unaPalabra) {
        textViewArtistas.setText("Artistas");
        textViewCanciones.setText("Canciones");
        textViewAlbumes.setText("Albumes");
        final AdapterArtista adapterArtista = new AdapterArtista(this);
        final ArtistaController artistaController = new ArtistaController(getActivity());
        final AdapterCancion adapterCancion = new AdapterCancion(this);
        final CancionController cancionController = new CancionController(getActivity());
        final AdapterAlbum adapterAlbum = new AdapterAlbum(this);
        final AlbumController albumController = new AlbumController();

        artistaController.buscarArtistas(unaPalabra, new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> results) {
                adapterArtista.setArtistaList(results);

            }
        });

        recyclerViewArtistas.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewArtistas.setAdapter(adapterArtista);

        cancionController.buscarCancion(unaPalabra, new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> results) {
                adapterCancion.setCancionList(results);

            }
        });

        recyclerViewCanciones.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerViewCanciones.setAdapter(adapterCancion);

        albumController.buscarAlbum(unaPalabra, new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> results) {
                adapterAlbum.setAlbumList(results);

            }
        });

        recyclerViewAlbumes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewAlbumes.setAdapter(adapterAlbum);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerDeFragment = (FragmentResultados.ListenerDeFragment) context;
    }

    @Override
    public void informarArtistaSeleccionado(Artista artista) {
        listenerDeFragment.mostrarArtistaBuscado(artista);
    }

    @Override
    public void informarCancionSeleccionada(Cancion cancion) {
        listenerDeFragment.mostrarCancionBuscada(cancion);
    }

    @Override
    public void informarAlbumSeleccionado(Album album) {
        listenerDeFragment.mostrarAlbumBuscado(album);
    }

    public interface ListenerDeFragment {
        void mostrarArtistaBuscado(Artista artista);

        void mostrarCancionBuscada(Cancion cancion);

        void mostrarAlbumBuscado(Album album);
    }
}