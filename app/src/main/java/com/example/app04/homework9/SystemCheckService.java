package com.example.app04.homework9;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.app04.utils.MySystemProperties;

import java.util.Properties;


public class SystemCheckService extends IntentService {
   private Properties properties;
    private static Class<?> mClassType = null;
    public SystemCheckService() {
        super("SystemCheckService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
       //执行检查
        Log.i("--SystemCheckService--","后台开始检查service运行");
        //读取 properties文件 进行设置，
        //通过反射得到这个类 android.os.SystemProperties
        String root = load("root");//获得预定义文件的数据。
        //进行系统修改。
        int sdkVersion = MySystemProperties.getSdkVersion();
        if (root.equals(String.valueOf(sdkVersion))){
            Toast.makeText(this,"系统配置一致",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"系统配置不匹配",Toast.LENGTH_SHORT).show();

        }

    }

    //根据String key   返回String value
    public String load(String key) {
        String value = null;
        if (properties==null){
            properties = new Properties();
        }
        try {
            properties.load(getAssets().open("a.properties"));
            value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}