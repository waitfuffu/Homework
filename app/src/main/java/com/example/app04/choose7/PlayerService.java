package com.example.app04.choose7;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.app04.R;
import com.example.app04.initmain.MainActivity;

public class PlayerService extends Service {
    private Messenger mActivityMessenger;
    private Handler handler;
    private Messenger mServiceMessenger;
    private MediaPlayer mp;
    private boolean isStop = false ;
    private int idPosition ;

    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("serviceCalculate");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x11) {
                    if (mActivityMessenger == null) {
                        mActivityMessenger = msg.replyTo;
                    }
                    //耗时任务,播放音乐
                    if (mp != null) {
                        mp.release();   //清空
                    }
                    //得到的msg.arg1 转化为R.id....
                    int[] ID = {R.raw.m1, R.raw.m2, R.raw.m3};
                    mp = MediaPlayer.create(PlayerService.this, ID[msg.arg1]);
                    idPosition = msg.arg1;
                    mp.start();
                    //因为按照先进先出的原则，第一个msg未完毕，则不会处理下一个msg，所以需要线程
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!isStop) {
                                try {
                                    //每100ms更新一次进度
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                int position = mp.getCurrentPosition();
                                int time = mp.getDuration();
                                int barMax = 100;
                                //计算出当前进度最大值为100
                                int sendPosition = position * barMax / time;
                                //发送数据回Activity
                                // Message message = this.obtainMessage();
                                Message message = Message.obtain();
                                message.what = 0x12;
                                message.arg1 = idPosition;
                                message.arg2 = sendPosition;
                                try {
                                    mActivityMessenger.send(message);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
                //暂停
                if (msg.what == 0x21) {
                    if (mp.isPlaying()) {
                        Log.i("---PlayerService---","stop");
                        mp.stop();
                    }

                }
                }

        };
        mServiceMessenger = new Messenger(handler);
    }

    @Override
    public IBinder onBind(Intent intent) {
        mServiceMessenger = new Messenger(handler);
        return mServiceMessenger.getBinder();
    }

    @Override
    public void onDestroy() {

    }
}