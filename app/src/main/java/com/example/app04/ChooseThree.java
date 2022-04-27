package com.example.app04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;

public class ChooseThree extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_choose_three);

        doNetListener();

        doDisPlay();

    }

    private void doNetListener() {




    }

    private void doDisPlay() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();     //取数据
        UserData user = (UserData)bundle.getSerializable("user");
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String[] hobbies = bundle.getStringArray("hobby");
        //判断  存取数据是否一致 int string  string[]
        if(user.getId()==id&&user.getName().equals(name)&& Arrays.equals(user.getHobby(),hobbies)){
            showInfo(user);
        }
    }


    private void showInfo(UserData user) {
    String[] ctype = new String[]{"id : "+String.valueOf(user.getId()),"name : "+user.getName(),
    "hobbys : "+Arrays.toString(user.getHobby())};    //读取得到 数据源

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,ctype);
        setListAdapter(adapter);

    }
}