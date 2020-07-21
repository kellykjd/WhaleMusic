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
import com.example.whalemusic.model.Cancion;
import com.example.whalemusic.model.Playlist;
import com.example.whalemusic.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallePlaylist extends Fragment  implements AdapterCancion.ListenerDelAdapterCancion{

    public static final String CLAVE_PLAYLIST="clavePlaylist";
    private RecyclerView recyclerViewCanciones;
    private TextView textViewTituloPlaylist;
    private ImageView imageViewFoto;
    private ListenerDeFragment listenerDeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_playlist, container, false);
        encontrarVistas(view);

        Bundle bundle = getArguments();
        Playlist playlistSeleccionado = (Playlist) bundle.getSerializable(CLAVE_PLAYLIST);
        Glide.with(getContext())
                .load(playlistSeleccionado.getPicture_medium())
                .into(imageViewFoto);
        textViewTituloPlaylist.setText(playlistSeleccionado.getTitle());


        final AdapterCancion adapterCancion = new AdapterCancion(this);
        CancionController cancionController = new CancionController(getActivity());
        cancionController.traerCancionesPlaylist(playlistSeleccionado.getId(), new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> results) {
                adapterCancion.setCancionList(results);
            }
        });

        recyclerViewCanciones.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        recyclerViewCanciones.setAdapter(adapterCancion);

        return view;
    }
    private void encontrarVistas(View view){
        textViewTituloPlaylist = view.findViewById(R.id.fragmentDetallePlaylist_TextView_nombre);
        imageViewFoto = view.findViewById(R.id.fragmentDetallePlaylist_ImageView_foto);
        recyclerViewCanciones = view.findViewById(R.id.fragmentDetallePlaylist_RecyclerView);
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

