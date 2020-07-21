package com.example.whalemusic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.whalemusic.R;
import com.example.whalemusic.model.BibliotecaViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ActivityBibliotecasViewPager extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibliotecas_view_pager);

        //Intent intent = getIntent();

        List<Fragment> listaDeFragments = new ArrayList<>();
        listaDeFragments.add(new FragmentViewPagerPlaylists());


        viewPager = findViewById(R.id.activityBibliotecas_viewPagerBibliotecas);
        tabLayout = findViewById(R.id.BibliotecaTabLayout);
        AdapterBibliotecasViewPager adapterBibliotecasViewPager = new AdapterBibliotecasViewPager(getSupportFragmentManager(), listaDeFragments);
        viewPager.setAdapter(adapterBibliotecasViewPager);
        tabLayout.setupWithViewPager(viewPager);
    }
}