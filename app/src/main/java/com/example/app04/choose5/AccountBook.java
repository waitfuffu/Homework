package com.example.app04.choose5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.app04.R;
import com.example.app04.databinding.AccountInfoBinding;
import com.example.app04.databinding.ActivityAccountBookBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountBook extends AppCompatActivity {
    private ActivityAccountBookBinding viewBinding;
    private Uri uri = Uri.parse("content://com.example.app04.provider/book");
    private AccountProvider provider;
    private ListView listview;
    private EditText et1;
    private EditText et2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityAccountBookBinding.inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
        listview = viewBinding.c5listview;
        et1 = viewBinding.c5edt1;
        et2 = viewBinding.c5edt2;
        btn = viewBinding.c5btn;
        provider = new AccountProvider();
        //创建数据库等初始化视图操作
        updateView();
        //添加操作
        insert();
    }


    public void updateView() {
        //list 用于适配的数据源
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Cursor cursor = getContentResolver().query(uri, null, null, null);
        if (cursor != null) {
            //获得对应 列 的id
            int goodsId = cursor.getColumnIndex("goods");
            int priceId = cursor.getColumnIndex("price");
            while (cursor.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                String goods = cursor.getString(goodsId);
                String price = cursor.getString(priceId);
                map.put("goods", goods);
                map.put("price", price);
                data.add(map);
            }
            cursor.close();
        }
      //  R.layout.simple_list_item_2 默认有两个TextView  ，text1,text2
        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                new String[]{"goods", "price"}, new int[]{android.R.id.text1, android.R.id.text2});
        listview.setAdapter(adapter);
    }


    public void insert() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                String goods = et1.getText().toString();
                String price = et2.getText().toString();
                values.put("goods",goods);
                values.put("price",price);
                getContentResolver().insert(uri,values);
                //更新信息View
                updateView();
            }
        });
    }
}