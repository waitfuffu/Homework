package com.example.app04.homework9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.activity.result.contract.ActivityResultContracts;

public class SelfStarting extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            Log.i("----SelfStarting----", "收到开机广播");
            //收到开机广播后 逻辑执行
            //1判断系统与预设置是否一致,启动对应的Service用于检测。
            //2开启一个Activity，其中动态注册接收自定义广播 ，开始检查内存使用情况
            //3新建一个自定义广播用于 通知对应应用开始检查内存使用情况

            //1开启Service
            Intent serviceIntent = new Intent(context, SystemCheckService.class);
            context.startService(serviceIntent);
            Log.i("----SelfStarting----", "执行逻辑1");
            //2开启一个Activity
            Intent activityIntent = new Intent(context, Memoryusage.class);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityIntent);
            Log.i("----SelfStarting----", "执行逻辑2");

            //3自定义广播
            Intent receiverIntent = new Intent("com.android.MyMemoryusage");//隐式不可以，需全类名
            receiverIntent.setPackage(context.getPackageName());//加入接收广播所在的包名
            context.sendBroadcast(intent);
            Log.i("----SelfStarting----", "执行逻辑3");


    }
}