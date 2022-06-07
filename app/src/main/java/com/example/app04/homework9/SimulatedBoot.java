package com.example.app04.homework9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.app04.R;

public class SimulatedBoot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulated_boot);
       Button button = (Button)findViewById(R.id.c9button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("----SimulatedBoot----","点击了模拟开机");
               //模拟开机行为发送广播
               Intent receiverIntent = new Intent("android.intent.action.monikaiji");//隐式不可以，需全类名
               receiverIntent.setPackage(getPackageName());//加入接收广播所在的包名
               sendBroadcast(receiverIntent);
           }
       });
    }
}