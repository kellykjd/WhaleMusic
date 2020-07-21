package com.example.whalemusic.view;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterOnBoarding extends FragmentStatePagerAdapter {

    private List<Fragment> listaDeFragmentsOnboarding;

    public AdapterOnBoarding(FragmentManager fm, List<Fragment> listaDeFragmentsOnboarding) {
        super(fm);

        this.listaDeFragmentsOnboarding = listaDeFragmentsOnboarding;

        listaDeFragmentsOnboarding = new ArrayList<>();
        FragmentViewPagerOnboarding1 fragmentViewPagerOnboarding1 = new FragmentViewPagerOnboarding1();
        FragmentViewPagerOnboarding2 fragmentViewPagerOnboarding2 = new FragmentViewPagerOnboarding2();
        FragmentViewPagerOnboarding3 fragmentViewPagerOnboarding3 = new FragmentViewPagerOnboarding3();
        listaDeFragmentsOnboarding.add(fragmentViewPagerOnboarding1);
        listaDeFragmentsOnboarding.add(fragmentViewPagerOnboarding2);
        listaDeFragmentsOnboarding.add(fragmentViewPagerOnboarding3);
    }


    @Override
    public Fragment getItem(int position) {
        return this.listaDeFragmentsOnboarding.get(position);
    }

    @Override
    public int getCount() {
        return this.listaDeFragmentsOnboarding.size();
    }

}
