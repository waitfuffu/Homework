package com.example.app04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

/**
 *
 */
public class ListFragment extends androidx.fragment.app.ListFragment {
    //默认为横屏 （ 两个fragment）
String[] title = new String[]{"亮度","声音","....."};


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,title));

    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position==0){
            DetailFragment detailFragment = new DetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.frameLay,detailFragment).commit();
        }

    }
}
