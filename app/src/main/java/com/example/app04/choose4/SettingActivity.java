package com.example.app04.choose4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.app04.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //R.array.c4imgids 通过XMLwen文件得到 ID 不正确,0开始
        //int[] imgIds = getResources().getIntArray(R.array.c4imgids);
        int[] imgIds = new int[]{R.drawable.wifi,R.drawable.blue,
                R.drawable.battery,R.drawable.volume,R.drawable.search};
        String[] itemTitles = getResources().getStringArray(R.array.c4title);
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgIds[i]);
            map.put("title", itemTitles[i]);
            list.add(map);
        }
        SimpleAdapter simAda = new SimpleAdapter(this, list, R.layout.listviewinfo,
                new String[]{"image","title"}, new int[]{R.id.c4images,R.id.c4textview});
       setListAdapter(simAda);
    }
}