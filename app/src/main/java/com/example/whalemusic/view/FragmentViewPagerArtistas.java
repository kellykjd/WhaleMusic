package com.example.whalemusic.view;


import  android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whalemusic.R;
import com.example.whalemusic.model.Artista;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerArtistas extends Fragment implements AdapterArtista.ListenerDelAdapterArtista{

    private RecyclerView recyclerViewCancion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment =  inflater.inflate(R.layout.fragment_view_pager_artistas, container, false);

        recyclerViewCancion = vistaDelFragment.findViewById(R.id.viewpagerArtistasRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerViewCancion.setLayoutManager(linearLayoutManager);
        AdapterArtista adapterArtista = new AdapterArtista(this);
        recyclerViewCancion.setAdapter(adapterArtista);

        return vistaDelFragment;
    }

    @Override
    public void informarArtistaSeleccionado(Artista artista) {

    }
}
