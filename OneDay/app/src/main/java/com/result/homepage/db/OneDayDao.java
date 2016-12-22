package com.result.homepage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.result.homepage.bean.CollectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-20.
 */
public class OneDayDao {

    private dbOpenHelper helper;
    private SQLiteDatabase sqLiteDatabase;


    public OneDayDao(Context context) {
        helper = new dbOpenHelper(context);
    }

    /**
     * 添加历史信息到数据库
     *
     */
    public void add(String date,String title,String url,String e_id){
        // 获取一个可读可写的数据库
        if (!same(title)) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("date",date);
        values.put("title",title);
        values.put("url",url);
        values.put("e_id",e_id);
        db.insert("oneday", null, values);
        db.close();
        }
    }
    /**
     * 查
     * 对数据库进行查询操作
     */
    public List<CollectionBean> selectSQL() {
        List<CollectionBean> list=new ArrayList<>();
        // 通过写数据库的方式,打开数据库,得到一个数据库对象
        sqLiteDatabase = helper.getWritableDatabase();
        /**
         * Table 表名 Columens 要返回的列 Selection 查询条件 selectionArgs 查询条件占位符对应的内容
         * groupBy 分组判断条件 Having 是否含有的判断条件 orderBy 排序判断条件 Limit 分页查询判断条件
         */
        // 返回值(Cursor):包含查询到的数据
        Cursor cursor=sqLiteDatabase.rawQuery("select * from oneday ",null);
        //结果集的下一条数据
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            String e_id = cursor.getString(cursor.getColumnIndex("e_id"));
            list.add(new CollectionBean(date,title,url,e_id));
            Log.i("***log***", "date = " + date + " title =" + title
                    +" url = "+url);
        }
        // 关闭数据库,释放资源
        sqLiteDatabase.close();
        return list;
    }

    /**
     * 根据学生的姓名删除学生信息
     * @param title
     */
    public void delete(String title){
        SQLiteDatabase  db=helper.getWritableDatabase();
        db.delete("oneday", "title=?", new String[]{title});
        db.close();
    }
    /**
            * 判断重复数据
    *
    */
    public boolean same(String title) {
        SQLiteDatabase db = helper.getReadableDatabase();
        // 結果集
        Cursor cursor = db.rawQuery("select * from oneday where title = ?", new String[]{title});
        // 如果游标可以向下移动的话，就一直查询
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
}
