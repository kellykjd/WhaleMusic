package com.example.whalemusic.view;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whalemusic.R;
import com.example.whalemusic.controller.CancionController;
import com.example.whalemusic.model.Album;
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleAlbum extends Fragment implements AdapterCancion.ListenerDelAdapterCancion{
    public static final String CLAVE_ALBUM = "claveAlbum";
    private RecyclerView recyclerViewCanciones;
    private TextView textViewTitle;
    private TextView textViewArtist;
    private ImageView imageViewCover;
    private ListenerDeFragment listenerDeFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_album, container, false);
        encontrarVistas(view);

        Bundle bundle = getArguments();
        Album albumSeleccionado = (Album) bundle.getSerializable(CLAVE_ALBUM);
        Glide.with(getContext())
                .load(albumSeleccionado.getCover_medium())
                .into(imageViewCover);
        textViewTitle.setText(albumSeleccionado.getTitle());
        textViewArtist.setText(albumSeleccionado.getArtist().getName());

        final AdapterCancion adapterCancion = new AdapterCancion(this);
        CancionController cancionController = new CancionController(getActivity());
        cancionController.traerCancionesAlbum(albumSeleccionado.getId(), new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> results) {
                adapterCancion.setCancionList(results);
            }
        });

        recyclerViewCanciones.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerViewCanciones.setAdapter(adapterCancion);
        return view;
    }

    private void encontrarVistas(View view) {
        textViewTitle = view.findViewById(R.id.fragmentDetalleAlbum_TextView_titulo);
        textViewArtist = view.findViewById(R.id.fragmentDetalleAlbum_TextView_artista);
        imageViewCover = view.findViewById(R.id.fragmentDetalleAlbum_ImageView_cover);
        recyclerViewCanciones= view.findViewById(R.id.fragmentDetalleAlbum_RecyclerView);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerDeFragment = (ListenerDeFragment) context;
    }

    @Override
    public void informarCancionSeleccionada(Cancion cancion) {
        listenerDeFragment.recibirCancion(cancion);
    }

    public interface ListenerDeFragment {
        void recibirCancion(Cancion cancion);
    }
}