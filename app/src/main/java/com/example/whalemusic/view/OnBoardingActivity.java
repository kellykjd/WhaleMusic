package com.example.whalemusic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.whalemusic.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPagerOnboarding;
    private TabLayout tabLayoutOnboarding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        List<Fragment> listaDeFragmentsOnboarding = new ArrayList<>();

        FragmentViewPagerOnboarding1 fragmentViewPagerOnboarding1 = new FragmentViewPagerOnboarding1();
        FragmentViewPagerOnboarding2 fragmentViewPagerOnboarding2 = new FragmentViewPagerOnboarding2();
        FragmentViewPagerOnboarding3 fragmentViewPagerOnboarding3 = new FragmentViewPagerOnboarding3();
        listaDeFragmentsOnboarding.add(fragmentViewPagerOnboarding1);
        listaDeFragmentsOnboarding.add(fragmentViewPagerOnboarding2);
        listaDeFragmentsOnboarding.add(fragmentViewPagerOnboarding3);

        viewPagerOnboarding = findViewById(R.id.viewpagerOnboarding);
        AdapterOnBoarding adapterOnBoarding = new AdapterOnBoarding(getSupportFragmentManager(), listaDeFragmentsOnboarding);
        viewPagerOnboarding.setAdapter(adapterOnBoarding);
    }
}
