package com.example.whalemusic.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.whalemusic.R;
import com.example.whalemusic.model.Album;
import com.example.whalemusic.model.BibliotecaViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerAlbumes extends Fragment implements AdapterAlbum.ListenerDelAdapterAlbum{

    //la clave para poner el serializable en el bundle
    public static final String CLAVE_VIEWPAGER_ALBUMES = "claveviewpageralbumes";

    private RecyclerView recyclerViewAlbumes;

    public FragmentViewPagerAlbumes() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment =  inflater.inflate(R.layout.fragment_view_pager_albumes, container, false);

        //Bundle bundle = getArguments();

        recyclerViewAlbumes = vistaDelFragment.findViewById(R.id.fragmentBibliotecaViewPagerRecyclerAlbumes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewAlbumes.setLayoutManager(linearLayoutManager);
        AdapterAlbum adapterAlbum = new AdapterAlbum(this);
        recyclerViewAlbumes.setAdapter(adapterAlbum);

        return vistaDelFragment;
    }

    @Override
    public void informarAlbumSeleccionado(Album album) {

    }
}