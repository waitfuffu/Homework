package com.example.app04.choose2;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.app04.utils.BrightnessUtil;

public class DetailFragment extends Fragment {
    private TextView textView ;
    private SeekBar seekBar ;
    private LinearLayout layout;
    private int position;

    public int getPosition() {
        return position;
    }

    public DetailFragment(int position) {
        this.position = position ;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init(this.position);
        return this.layout;

    }

    //页面初始化
    public void init(int position){
        switch (position) {
            case 0:
                doZeroTODO();
                break;
            case 1:
                doOneTODO();
                break;
            default:
                doOtherTODO();
                break;
        }

    }

    private void doOtherTODO() {

        layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        textView = new TextView(getActivity());
        textView.setText("未完成功能");
        textView.setTextSize(50);
        layout.addView(textView);
    }

    private void doOneTODO() {
        layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        textView = new TextView(getActivity());
        textView.setText("音量为 ：30"); //模拟音量
        textView.setTextSize(50);
        layout.addView(textView);

    }

    private void doZeroTODO() {
        layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        seekBar = new SeekBar(getActivity());
        textView = new TextView(getActivity());
        //系统最大屏幕亮度值设为seekbar的最大进度值
        seekBar.setMax(BrightnessUtil.getMaxBrightness(getActivity()));
        //系统当前屏幕亮度值设为seekbar当前进度值
        seekBar.setProgress(BrightnessUtil.getBrightness(getActivity()));
        //设置当前屏幕亮度显示
        textView.setText("值为 ："+BrightnessUtil.getBrightness(getActivity()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //亮度跟着改变
                textView.setText("值为 ："+progress);
                //借助工具类BrightnessUtil改变亮度
                // （只会修改当前Activity的亮度，不会修改系统亮度）
                BrightnessUtil.SetSystemLight(progress,getActivity());

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        layout.addView(seekBar);
        layout.addView(textView);

    }
}