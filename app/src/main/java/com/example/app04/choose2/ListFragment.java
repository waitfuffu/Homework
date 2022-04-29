package com.example.app04.choose2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.app04.R;

//左边ListFragment
public class ListFragment extends androidx.fragment.app.ListFragment {
    //默认为横屏 （ 两个fragment）
    String[] title = new String[]{"亮度", "声音", "....."};//设置选项

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, title));

    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        DetailFragment details = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.frameLay);
        if (details == null || details.getPosition() != position) {
            details = new DetailFragment(position);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLay, details);//内容替换
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//转换特效
            ft.commit();
        }
    }
}
