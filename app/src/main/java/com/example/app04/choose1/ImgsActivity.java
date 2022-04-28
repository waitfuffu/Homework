package com.example.app04.choose1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.app04.R;
import com.example.app04.databinding.ActivityImgsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImgsActivity extends AppCompatActivity {

    private ActivityImgsBinding mViewBinding;
    // 为了删除元素方便 ，用集合
    private List<Integer> mImgIdList;
    private GridView mGridView;
    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityImgsBinding.inflate(LayoutInflater.from(this));
        setContentView(mViewBinding.getRoot());
        mGridView = mViewBinding.gridview;
        proBar = mViewBinding.probar;

        // AsyncTask  异步操作
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // 1s之后进度条走满
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                proBar.setProgress((i * 10)+10);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            proBar.setVisibility(View.GONE);
            // 进行 UI 操作
            // 数据初始化
            init();
            // mGridView加载图片
            setAdapter(mGridView);
            // mGridView监听方法
            gridOnItemLongClickL(mGridView);
        }
    }

    private void init() {
        // 定义6张图片id 数组
        // 因为经过 Arrays.asList 方法创建的 list 不允许删除元素，
        // 所以利用中间temp量，存入新的mImgIdList   （不想用add方法一个个添加）
        List<Integer> tempList = Arrays.asList(new Integer[]{
                R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i6, R.drawable.i1
        });
        mImgIdList = new ArrayList<>();
        mImgIdList.addAll(tempList);
    }
    //长按监听器
    private void gridOnItemLongClickL(GridView mGridView) {
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alert = new AlertDialog.Builder(ImgsActivity.this).create();
                // 对话框要删除的图标
                alert.setIcon(mImgIdList.get(position));
                // 对话框标题
                alert.setTitle("删除");
                alert.setMessage("是否要删除这张图片 ？ ");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ImgsActivity.this, "已取消操作",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ImgsActivity.this, "删除成功",
                                        Toast.LENGTH_SHORT).show();
                                // 删除集合元素
                                mImgIdList.remove(position);
                                // 重新加载mGridView适配器
                                ImgsActivity.this.setAdapter(mGridView);
                            }
                        });
                alert.show();
                return true;
            }
        });
    }
    // 创建 BaseAdapter 并修改gridview
    private void setAdapter(GridView mGridView) {
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mImgIdList.size();
            }
            @Override
            public Object getItem(int position) {
                return position;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {
                    imageView = new ImageView(ImgsActivity.this);
                    // 设置缩放方式
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setPadding(5, 0, 5, 0);
                } else {
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource(mImgIdList.get(position));
                return imageView;
            }
        };
        mGridView.setAdapter(baseAdapter);
    }
}