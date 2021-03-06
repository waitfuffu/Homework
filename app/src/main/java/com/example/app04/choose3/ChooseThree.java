package com.example.app04.choose3;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.app04.bean.UserData;

import java.util.Arrays;

public class ChooseThree extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // ListActivity 不用调用setContentView 加载
        //  setContentView(R.layout.activity_choose_three);
        initReceiver();  //监听net
        doDisPlay();      //显示信息
    }

    /**
     * 注册网络监听的广播
     * （百度）
     */
    private void initReceiver() {
        IntentFilter timeFilter = new IntentFilter();
        timeFilter.addAction("android.net.ethernet.ETHERNET_STATE_CHANGED");
        timeFilter.addAction("android.net.ethernet.STATE_CHANGE");
        timeFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        timeFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        timeFilter.addAction("android.net.wifi.STATE_CHANGE");
        timeFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(netReceiver, timeFilter);
    }

    @Override
    protected void onDestroy() {
        //Activity销毁时，关闭监听 net
        super.onDestroy();
        if (netReceiver != null) {
            unregisterReceiver(netReceiver);
            netReceiver = null;
        }
    }

    /**
     * （百度查阅）
     */
    BroadcastReceiver netReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(
                        Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isAvailable()) {
                    int type2 = networkInfo.getType();
                    String typeName = networkInfo.getTypeName();
                    switch (type2) {
                        case 0://移动 网络    2G 3G 4G 都是一样的 实测 mix2s 联通卡
                            Log.d("Feeee", "有网络");
                            Toast.makeText(ChooseThree.this, "移动网络", Toast.LENGTH_SHORT).show();

                            break;
                        case 1: //wifi网络
                            Log.d("Feeee", "wifi");
                            Toast.makeText(ChooseThree.this, "wifi", Toast.LENGTH_SHORT).show();
                            break;

                        case 9:  //网线连接
                            Log.d("Feeee", "有网络");
                            Toast.makeText(ChooseThree.this, "网线", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {// 无网络
                    Log.d("Feeee", "无网络");
                    Toast.makeText(ChooseThree.this, "无网络", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void doDisPlay() {
        //取出数据并比对
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();     //取数据
        UserData user = (UserData) bundle.getSerializable("user");
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String[] hobbies = bundle.getStringArray("hobby");
        //判断  存取数据是否一致 int string  string[]
        if (user.getId() == id && user.getName().equals(name) && Arrays.equals(user.getHobby(), hobbies)) {
            showInfo(user);
        }
    }

    private void showInfo(UserData user) {
        //使用ListView 用于显示接收的数据
        String[] ctype = new String[]{"id : " + String.valueOf(user.getId()), "name : " + user.getName(),
                "hobbys : " + Arrays.toString(user.getHobby())};    //读取得到 数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_single_choice, ctype);
        setListAdapter(adapter);
    }
}