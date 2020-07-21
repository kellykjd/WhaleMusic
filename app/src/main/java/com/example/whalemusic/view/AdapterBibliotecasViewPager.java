package com.example.whalemusic.view;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.whalemusic.model.Artista;
import com.example.whalemusic.model.BibliotecaViewPager;

import java.util.ArrayList;
import java.util.List;

public class AdapterBibliotecasViewPager extends FragmentStatePagerAdapter {

    private List<Fragment> listaDeFragments;

    public AdapterBibliotecasViewPager(FragmentManager fm, List<Fragment> listaDeFragments) {
        super(fm);
        this.listaDeFragments = listaDeFragments;

        listaDeFragments = new ArrayList<>();

        FragmentViewPagerPlaylists fragmentViewPagerPlaylists = new FragmentViewPagerPlaylists();
        FragmentViewPagerArtistas fragmentViewPagerArtistas = new FragmentViewPagerArtistas();
        FragmentViewPagerAlbumes fragmentViewPagerAlbumes = new FragmentViewPagerAlbumes();

        listaDeFragments.add(fragmentViewPagerPlaylists);
        listaDeFragments.add(fragmentViewPagerArtistas);
        listaDeFragments.add(fragmentViewPagerAlbumes);
    }

    @Override
    public Fragment getItem(int position) {
        return this.listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return this.listaDeFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String titulo = "";
        switch (position) {
            case 0:
                titulo = "Favoritos";
                break;
            case 1:
                titulo = "Artistas";
                break;
            case 2:
                titulo = "Albumes";
                break;
        }
        return titulo;
    }
}