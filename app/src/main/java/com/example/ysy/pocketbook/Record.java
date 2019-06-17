package com.example.ysy.pocketbook;

import android.util.Log;

import java.io.Serializable;
import java.util.UUID;

public class Record implements Serializable {
    public static String TAG = "Record";

    public enum RecordType{
        RECORD_TYPE_EXPENSE,RECORD_TYPE_INCOME
    }

    private double amount;
    private RecordType type;
    private String category;//消费类型
    private String remark;
    private String date;//2019-06-10

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        if(this.type == RecordType.RECORD_TYPE_EXPENSE){
            return 1;
        }else {
            return 2;
        }
    }

    public void setType(int type) {
        if(type == 1){
            this.type = RecordType.RECORD_TYPE_EXPENSE;
        }else {
            this.type = RecordType.RECORD_TYPE_INCOME;
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private long timeStamp;//时间戳
    private String uuid;//每笔账目都有一个唯一的id

    public Record(){
        uuid = UUID.randomUUID().toString();
        Log.i(TAG, "Record: uuid =");
        timeStamp = System.currentTimeMillis();//返回1970年到现在的微秒
        Log.i(TAG, "Record: timeStamp = "+DateUtil.getFormattedTime(timeStamp));
        date = DateUtil.getFormattedDate();
        Log.i(TAG, date+" "+DateUtil.getFormattedTime(timeStamp));
    }
}
