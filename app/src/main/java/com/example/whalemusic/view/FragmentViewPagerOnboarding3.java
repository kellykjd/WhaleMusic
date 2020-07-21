package com.example.whalemusic.view;


import android.content.Intent;
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
public class FragmentViewPagerOnboarding3 extends Fragment {

    private ImageView imageView;


    public FragmentViewPagerOnboarding3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_view_pager_onboarding3, container, false);

        ImageView imageView = vistaDelFragment.findViewById(R.id.fragmentOnBoarding3_image);
        imageView.setImageResource(R.drawable.onboarding3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return vistaDelFragment;
    }

}
