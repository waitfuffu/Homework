package com.example.app04.choose5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class AccountDataHelper extends SQLiteOpenHelper {
    private Context mContext;
    //创建表的 SQL语句
    private String createBook = "create table Book(" +
            "id integer primary key autoincrement," +
            "goods text,"+
            "price text)";

    public AccountDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行SQL 创建Book表
        db.execSQL(createBook);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
