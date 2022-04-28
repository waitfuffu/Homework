package com.example.app04.initmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding viewBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding =ActivityMainBinding .inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
       EditText uid = viewBinding.uid1;
       EditText upwd = viewBinding.upwd1;
       Button btu= viewBinding.but1;

       //登录按钮监听
        btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("账号-密码 ：",uid.getText()+"-"+ upwd.getText()); //获得账号密码，和数据库进行对比：默认正确
                //进行跳转Activity,创建Intent 对象
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });









    }
}