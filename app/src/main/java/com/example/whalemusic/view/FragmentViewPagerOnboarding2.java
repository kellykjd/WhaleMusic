package com.example.whalemusic.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.whalemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewPagerOnboarding2 extends Fragment {

    public FragmentViewPagerOnboarding2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_view_pager_onboarding2, container, false);

        ImageView imageView = vistaDelFragment.findViewById(R.id.fragmentOnBoarding2_image);
        imageView.setImageResource(R.drawable.onboarding2);

        return vistaDelFragment;
    }
}