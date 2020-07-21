
package com.example.whalemusic.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whalemusic.controller.PlaylistController;
import com.example.whalemusic.model.Genero;
import com.example.whalemusic.R;
import com.example.whalemusic.model.Playlist;
import com.example.whalemusic.utils.ResultListener;
import java.util.List;

// A simple {@link Fragment} subclass.

public class FragmentDetalleGenero extends Fragment implements AdapterPlaylist.ListenerDelAdapterPlaylist {

    public static final String CLAVE_GENERO = "claveGenero";
    private RecyclerView recyclerViewPlaylist;
    private FragmentDetalleGenero.ListenerDeFragment listenerDeFragment;
    private TextView nombreGenero;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_genero, container, false);
        encontrarVistas(view);
        Bundle bundle = getArguments();
        Genero generoSeleccionado = (Genero) bundle.getSerializable(CLAVE_GENERO);
        final AdapterPlaylist adapterPlaylist = new AdapterPlaylist(this);
        PlaylistController playlistController = new PlaylistController(getActivity());

        playlistController.traerPlaylist(generoSeleccionado.getName(), new ResultListener<List<Playlist>>() {
            @Override
            public void finish(List<Playlist> result) {
                adapterPlaylist.setPlaylistList(result);
            }
        });

        recyclerViewPlaylist.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewPlaylist.setAdapter(adapterPlaylist);
        nombreGenero.setText(generoSeleccionado.getName());

        return view;
    }

    private void encontrarVistas(View view) {
        recyclerViewPlaylist = view.findViewById(R.id.fragmentDetalleGenero_RecyclerView_playlist);
        nombreGenero = view.findViewById(R.id.fragmentDetalleGenero_TextView_nombre);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerDeFragment = (FragmentDetalleGenero.ListenerDeFragment) context;
    }

    @Override
    public void informarPlaylistSeleccionado(Playlist playlist) {
        listenerDeFragment.recibirPlaylist(playlist);
    }

    public interface ListenerDeFragment {
        void recibirPlaylist(Playlist playlist);

    }
}