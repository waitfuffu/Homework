package com.example.app04.choose7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.app04.R;
import com.example.app04.databinding.ActivityPlayerBinding;
import com.example.app04.initmain.InfoActivity;

import java.io.Serializable;

public class PlayerActivity extends AppCompatActivity {
    private ActivityPlayerBinding viewBinding;
    private Spinner spinner;
    private Button b1;
    private Button b2;
    private TextView stateText;
    private ProgressBar pgb;
    private MainHandler mainHandler;
    private CharSequence[] array;
    boolean isStop = false;
    Intent intent;
    //Service端的Messenger对象
    private Messenger mServiceMessenger;
    //Activity端的Messenger对象
    private Messenger mActivityMessenger;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取Service端的Messenger
            Log.i("----IBinder-------", service.toString());
            mServiceMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x12) {
                int idPosition = msg.arg1;
                int barPosition = msg.arg2;
                Log.i("----MainHandler----",array[idPosition].toString());
                //更改文字状态
                stateText.setText("播放中: " + array[idPosition] + "  " + String.valueOf(barPosition) + "%");
                //更新进度条
                //pgb.setVisibility(View.VISIBLE);
                pgb.setProgress(barPosition);
            }
                super.handleMessage(msg);
        }
    }

    @Override
    public void onBackPressed() {
        //首先Activity启动方式切换为 singleTask
        //其次重写 back 方法，它默认 finish销毁Activity，返回状态不可调用finish()
        //返回上一级
        Intent intent = new Intent(PlayerActivity.this, InfoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityPlayerBinding.inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
        Log.i("----PlayerActivity-----","onCreate方法被执行了");
        init();
        buttonOnClickListener();
    }

    public void init() {
        spinner = viewBinding.c7spinner1;
        b1 = viewBinding.c7b1;
        b2 = viewBinding.c7b2;
        stateText = viewBinding.c7text;
        pgb = viewBinding.c7bar;
        array = getResources().getTextArray(R.array.c7musiclist);
        //首次创建时进度条消失
        pgb.setVisibility(View.GONE);
        //用于其他线程更新处理 UI
        mainHandler = new MainHandler();
        //绑定
        intent = new Intent(PlayerActivity.this, PlayerService.class);
        //不能在OnClick中，因为connection中的方法后执行
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    public void buttonOnClickListener() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setEnabled(true);
                pgb.setVisibility(View.VISIBLE);
                //Activity端的Messenger
                if (mActivityMessenger == null) {
                    mActivityMessenger = new Messenger(mainHandler);
                }
                int itemPosition = spinner.getSelectedItemPosition();
                //创建消息
                Message message = Message.obtain();
                message.what = 0x11;
                message.arg1 = itemPosition;
                Log.i("-----itemPosition----", String.valueOf(itemPosition));
                //设定消息要回应的Messenger
                message.replyTo = mActivityMessenger;
                try {
                    //通过ServiceMessenger将消息发送到Service中的Handler
                    Log.i("----PlayerActivity----", "startButtonMsg");
                    mServiceMessenger.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgb.setVisibility(View.GONE);
                stateText.setVisibility(View.GONE);
                Message message = Message.obtain();
                message.what = 0x21;
                message.replyTo = mActivityMessenger;
                try {
                    mServiceMessenger.send(message);
                    Log.i("----PlayerActivity----","sendStopMsg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (connection != null) {
            //解绑
            unbindService(connection);
        }
        super.onDestroy();
    }
}