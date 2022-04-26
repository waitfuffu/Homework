package com.example.app04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 *
 */
public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getActivity());

        layout.setOrientation(LinearLayout.VERTICAL);
        SeekBar seekBar = new SeekBar(getActivity());
        TextView textView = new TextView(getActivity());



        layout.addView(seekBar);
        layout.addView(textView);

        return layout;

    }
}