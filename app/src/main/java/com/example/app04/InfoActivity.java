package com.example.app04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.app04.databinding.ActivityInfoBinding;
public class InfoActivity extends AppCompatActivity {
    private ActivityInfoBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityInfoBinding.inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
        ListView listView = viewBinding.listview1;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.ctype, android.R.layout.simple_list_item_checked
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                Toast.makeText(InfoActivity.this,"点击了："+s,Toast.LENGTH_SHORT).show();
                  indexToDo(position);      //根据 position TODO

            }
        });
    }


   public void indexToDo(int position){
       if (position==0){   //点击了第一个选项 ,跳转对应Activity
           Intent intent = new Intent(InfoActivity.this,ImgsActivity.class);
           startActivity(intent);  //gridView 来显示图片
       }

       if (position==1){ //点击了第二个选项 ,跳转对应Activity
        Intent intent = new Intent(InfoActivity.this,IndexTwoActivity.class);

        Bundle bundle = new Bundle();       //保存数据
        bundle.putString("index2","第二个选项传输的字符串");
        intent.putExtras(bundle);

        startActivity(intent);

       }

   };


}

