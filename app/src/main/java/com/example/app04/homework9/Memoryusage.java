package com.example.app04.homework9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app04.R;
import com.example.app04.databinding.ActivityMemoryusageBinding;

public class Memoryusage extends AppCompatActivity {
    private ActivityMemoryusageBinding viewBinding;
    private MemoryusageReceiver receiver;
    private LinearLayout mLayout;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private String maxMemory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMemoryusageBinding.inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
        t1 = viewBinding.c9t1;
        t2 = viewBinding.c9t2;
        t3 = viewBinding.c9t3;
        t4 = viewBinding.c9t4;
        mLayout = viewBinding.h9lin;
        // maxMemory = getResources().getString(R.string.);
        // 动态注册接收广播
        IntentFilter intentFilter = new IntentFilter();//过滤器
        intentFilter.addAction("com.android.MyMemoryusage");//自定义
        receiver = new MemoryusageReceiver();
        registerReceiver(receiver, intentFilter);//进行广播接收注册
    }

    class MemoryusageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到自定义广播执行逻辑
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            float maxMemory1 = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
            t2.setText("最大分配内存为:" + maxMemory1);
            float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
            t3.setText("当前分配的总内存为:" + totalMemory);
            float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
            t4.setText("剩余内存为:" + freeMemory);
            //100s 内 ，每秒更新一次
            for (int i = 0; i < 100; i++) {
                TextView textView = new TextView(Memoryusage.this);
                textView.setText(i + "s系统剩余内存为：" + String.valueOf(memoryInfo.availMem / (1024 * 1024)) + "MB");
                mLayout.addView(textView);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }
}