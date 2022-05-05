package com.example.app04.initmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app04.choose5.AccountBook;
import com.example.app04.R;
import com.example.app04.bean.UserData;
import com.example.app04.choose1.ImgsActivity;
import com.example.app04.choose2.IndexTwoActivity;
import com.example.app04.choose3.ChooseThree;
import com.example.app04.choose4.SettingActivity;
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
                this, R.array.ctype, android.R.layout.simple_list_item_checked
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                Toast.makeText(InfoActivity.this, "点击了：" + s, Toast.LENGTH_SHORT).show();
                indexToDo(position);      //根据 position TODO
            }
        });
    }


    public void indexToDo(int position) {
        switch (position) {
            case 0:
                doOneTODO();
                break;
            case 1:
                doTwoTODO();
                break;
            case 2:
                doThreeTODO();
                break;
            case 3:
                doFourTODO();
                break;
            case 4:
                doFiveTODO();
                break;
            default:
                break;
        }
    }

    private void doFiveTODO() {
    Intent intent = new Intent(InfoActivity.this, AccountBook.class);
    startActivity(intent);
    }

    //点击了第四个选项 ,跳转对应Activity
    private void doFourTODO() {
        Intent intent = new Intent(InfoActivity.this, SettingActivity.class);
        startActivity(intent);
    }


    //点击了第一个选项 ,跳转对应Activity
    private void doOneTODO() {
        Intent intent = new Intent(InfoActivity.this, ImgsActivity.class);
        startActivity(intent);  //gridView 来显示图片
    }

    //点击了第二个选项 ,跳转对应Activity
    private void doTwoTODO() {
        Intent intent = new Intent(InfoActivity.this, IndexTwoActivity.class);
        Bundle bundle = new Bundle();       //保存数据
        bundle.putString("index2", "第二个选项传输的字符串");
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //点击了第三个选项 ,跳转对应Activity
    private void doThreeTODO() {
        Intent intent = new Intent(InfoActivity.this, ChooseThree.class);
        Bundle bundle = new Bundle();
        //准备传递的数据
        //bean 下 Data类 用于存储数据，同时用于Serializable传输的对象
        UserData user = new UserData(1001, "zs", new String[]{"篮球", "跑步"});
        bundle.putInt("id", user.getId());
        bundle.putString("name", user.getName());
        bundle.putStringArray("hobby", user.getHobby());
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}

