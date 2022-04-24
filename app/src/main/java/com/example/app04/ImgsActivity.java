package com.example.app04;

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

import com.example.app04.databinding.ActivityImgsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImgsActivity extends AppCompatActivity {
    private ActivityImgsBinding viewBinding;
    private List<Integer> imgIdList;        //为了删除元素方便 ，用集合
    private GridView gridView ;
    ProgressBar probar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityImgsBinding.inflate(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());
        gridView = viewBinding.gridview;
        probar = viewBinding.probar;

        // AsyncTask  异步操作
        new MyTack().execute();

    }

    class MyTack extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            probar.setVisibility(View.VISIBLE);

        }


        @Override
        protected Void doInBackground(Void... voids) {
            //1s之后进度条走满
            for (int i =0;i<=10;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                probar.setProgress(i*10);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            probar.setVisibility(View.GONE);
//进行 UI 操作
            //数据初始化
            init();

            //gridView加载图片
            setAdapter(gridView);

            //gridView监听方法
            gridOnItemLongClickL(gridView);

        }
    }

    private void init() {
        //定义6张图片id 数组
        //因为经过 Arrays.asList 方法创建的 list 不允许删除元素，
        // 所以利用中间temp量，存入新的imgIdList   （不想用add方法一个个添加）
        List<Integer> tempList = Arrays.asList(new Integer[] {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4,R.drawable.i6,R.drawable.i1});
        imgIdList = new ArrayList<>();
        imgIdList.addAll(tempList);
    }


    //长按监听器

    private void gridOnItemLongClickL(GridView gridView) {
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alert = new AlertDialog.Builder(ImgsActivity.this).create();
                alert.setIcon(imgIdList.get(position));    //对话框要删除的图标
                alert.setTitle("删除");      //对话框标题
                alert.setMessage("是否要删除这张图片 ？ ");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ImgsActivity.this,"已取消操作",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ImgsActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        imgIdList.remove(position);     //删除集合元素
                        //重新加载gridView适配器
                        ImgsActivity.this.setAdapter(gridView);
                    }
                });
                alert.show();
                return true;
            }
        });

    }


    //创建 BaseAdapter 并修改gridview
    private void setAdapter(GridView gridView){
        BaseAdapter baseAdapter = new BaseAdapter() {

            @Override
            public int getCount() {
                return imgIdList.size();
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
                ImageView imageView ;
                if (convertView==null){
                    imageView = new ImageView(ImgsActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);//设置缩放方式
                    imageView.setPadding(5,0,5,0);
                }else {
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource(imgIdList.get(position));
                return imageView;
            }
        };
        gridView.setAdapter(baseAdapter);
    }
}