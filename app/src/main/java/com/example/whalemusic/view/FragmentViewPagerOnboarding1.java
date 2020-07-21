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

public class FragmentViewPagerOnboarding1 extends Fragment {

    public FragmentViewPagerOnboarding1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaDelFragment = inflater.inflate(R.layout.fragment_view_pager_onboarding1, container, false);

        ImageView imageView = vistaDelFragment.findViewById(R.id.fragmentOnBoarding1_image);
        imageView.setImageResource(R.drawable.onboarding1);

        return vistaDelFragment;
    }
}