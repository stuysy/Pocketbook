package com.example.ysy.pocketbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;

public class RecordDatabaseHelper extends SQLiteOpenHelper {
    private static String TAG = "RecordDatabaseHelper";
    public static final String DB_NAME = "Record";//数据库名称
    //用sql语句创建数据库的表
    private static final String CREATE_RECORD_DB = "create table Record ("
            + "id integer primary key autoincrement, " //ID主键，自增
            + "uuid text, " //
            + "type integer, " // 1是提升，2是降低
            + "category text, "
            + "remark text, "
            + "score integer, "
            + "time integer, "
            + "date date )";

    public RecordDatabaseHelper( Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECORD_DB);//建立数据库
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {//i i1是版本号，用于更新升级

    }

    //增加记录
    public void addRecord(Record bean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uuid",bean.getUuid());
        values.put("type",bean.getType());
        values.put("category",bean.getCategory());
        values.put("remark",bean.getRemark());
        values.put("score",bean.getScore());
        values.put("date",bean.getDate());
        values.put("time",bean.getTimeStamp());
        db.insert(DB_NAME,null,values);
        values.clear();
        Log.i(TAG,bean.getUuid()+"added");
    }

    //删除记录
    public void  removeRecord(String uuid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME,"uuid = ?",new String[]{uuid});
    }

    //编辑记录
    public void editRecord(String uuid,Record record){
        removeRecord(uuid);
        record.setUuid(uuid);
        addRecord(record);
    }

    //用一个链表储存日期的数组，来查询每天的数据
    public LinkedList<Record> readRecords(String dateStr){
        LinkedList<Record> records = new LinkedList<>();

        SQLiteDatabase db = this.getWritableDatabase();//获取当前数据库
        //实例化一个cursor，用于承接返回数据
        Cursor cursor = db.rawQuery("select DISTINCT * from Record where date = ? order by time asc",new String[]{dateStr});
        if (cursor.moveToFirst()){
            do{
                String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));
                int score = cursor.getInt(cursor.getColumnIndex("amount"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                long timeStamp = cursor.getLong(cursor.getColumnIndex("time"));

                Record record = new Record();//实例化一个Record，接收返回数据
                record.setUuid(uuid);
                record.setType(type);
                record.setCategory(category);
                record.setRemark(remark);
                record.setScore(score);
                record.setDate(date);
                record.setTimeStamp(timeStamp);

                records.add(record);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return records;
    }

    //返回一个包含所有日期的列表
    public LinkedList<String> getAvaliableDate(){
        LinkedList<String> dates = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //请求所有的记录
        Cursor cursor = db.rawQuery("select DISTINCT * from Record order by date asc",new String[]{});
        if (cursor.moveToFirst()){
            do{
                String date = cursor.getString(cursor.getColumnIndex("date"));
                if (!dates.contains(date)){//如果不包含这个日期,则添加它
                    dates.add(date);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dates;
    }

}
