package com.result.homepage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 贾焕雪 on 2016-12-20.
 */
public class dbOpenHelper extends SQLiteOpenHelper{
    public dbOpenHelper(Context context) {
        super(context,"oneday.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table oneday (id integer primary key autoincrement,date varchar(20),title varchar(20),url varchar(100),e_id varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
