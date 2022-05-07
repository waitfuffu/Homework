package com.example.app04.choose6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app04.R;
import com.example.app04.databinding.ActivityDoMusicBinding;
import com.example.app04.initmain.MainActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DoMusic extends AppCompatActivity {
    private ActivityDoMusicBinding viewBinding;
    private EditText c6Edt;
    private Button c6Btn1;
    private Button c6Btn2;
    private TextView c6Num;
    private ProgressBar c6Psb;
    private TextView c6Gress;
    private int gress;
    private int num;
    //输入框内的地址
    private String editPath;
    //文件保存地址
    //所有的文件都默认存储到/data/data/<package name>/files/目录下,只需文件名即可
    private String targetFileAbsPath = "i.mp3";
    //停止标志
    private boolean isStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityDoMusicBinding.inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
        c6Edt = viewBinding.c6edt;
        c6Btn1 = viewBinding.c6btn1;
        c6Btn2 = viewBinding.c6btn2;
        c6Num = viewBinding.c6num;
        c6Psb = viewBinding.c6probar;
        c6Gress = viewBinding.c6gress;
        //按钮1,2点击事件
        buttonOnClickListener();
    }

    private void buttonOnClickListener() {
        //按钮一
        c6Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c6Btn1.setEnabled(false);
                editPath = c6Edt.getText().toString();
                // AsyncTask  异步操作
                new DownTask().execute(editPath, targetFileAbsPath);
                c6Btn2.setEnabled(true);

            }
        });

        //按钮2
        c6Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  c6Btn1.setEnabled(true);
                isStop = !isStop;
                if (isStop) {
                    c6Btn2.setEnabled(false);
                }
            }
        });
    }

    class DownTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPostExecute(Boolean unused) {
            super.onPostExecute(unused);
            c6Psb.setVisibility(View.GONE);
            c6Gress.setVisibility(View.GONE);
            if (!unused) {
                Toast.makeText(DoMusic.this, "下载失败", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(DoMusic.this, "下载完成", Toast.LENGTH_SHORT).show();
            //######下载完成开始播放音乐线程
            new MusicTask().execute();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //显示进度条与进度值
            c6Psb.setVisibility(View.VISIBLE);
            c6Gress.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            c6Psb.setProgress(values[0]);
            c6Gress.setText("已完成 :" + values[0].toString() + " %");
        }


        @Override
        protected Boolean doInBackground(String... voids) {
            int count;
            try {
                URL url = new URL(voids[0].toString());
                URLConnection conn = url.openConnection();
                conn.connect();
                int contentLength = conn.getContentLength();
                BufferedInputStream input = new BufferedInputStream(url.openStream());
                //FileOutputStream output = new FileOutputStream(voids[1]);
                //若文件名相同，覆盖同名文件
                FileOutputStream output = openFileOutput(voids[1].toString(), MODE_PRIVATE);
                byte[] buffer = new byte[1024];
                int total = 0;
                while ((count = input.read(buffer)) != -1) {
                    total += count;
                    output.write(buffer, 0, count);
                    //进度值通过publishProgress 给主线程UI控制
                    publishProgress((100 * (total / contentLength)));
                    Log.i("-----", "how many times while");
                    Thread.sleep(10);
                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    class MusicTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            c6Num.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            c6Num.setText("音乐播放中，循环次数:" + values[0].toString());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (!isStop) {
                MediaPlayer mp = null;
                if (mp != null) {
                    mp.release();   //清空
                }
                try {
                    //文件默认存储到/data/data/<package name>/files/i.mp3
                    mp = new MediaPlayer();
                    mp.setDataSource("/data/data/com.example.app04/files/i.mp3");
                    mp.setDataSource("/files/i.mp3");
                    //两种方式都出现了异常，文件夹里有i.mp3文件 ，未解决
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(DoMusic.class.toString() + "---------", "mp的创建异常");
                }
                //用id 的方式指定可运行
                mp = MediaPlayer.create(DoMusic.this, R.raw.i2);
                mp.start();
                //mp结束监听器
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        try {
                            //音乐结束n时间后进行重播
                            Thread.sleep(0);
                            num++;
                            publishProgress(num);
                            doInBackground();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
            return null;
        }
    }
}

