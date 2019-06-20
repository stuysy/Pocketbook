package com.example.ysy.pocketbook;

import android.util.Log;

import java.io.Serializable;
import java.util.UUID;

public class Record implements Serializable {
    public static String TAG = "Record";

    public enum RecordType{
        RECORD_TYPE_INCREASE,RECORD_TYPE_DECREASE//提升和降低
    }

    private int score;//打分
    private RecordType type;//提升或降低
    private String category;//具体项目类别：学习、技能....

    private String remark;//备注
    private String date;//2019-06-10

    private long timeStamp;//时间戳
    private String uuid;//每个事件都有一个唯一的id


    public Record(){
        uuid = UUID.randomUUID().toString();
        Log.i(TAG, "Record: uuid =");
        timeStamp = System.currentTimeMillis();//返回1970年到现在的微秒
        Log.i(TAG, "Record: timeStamp = "+DateUtil.getFormattedTime(timeStamp));
        date = DateUtil.getFormattedDate();
        Log.i(TAG, date+" "+DateUtil.getFormattedTime(timeStamp));
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getType() {//本来是枚举类，但是数据库不支持，所以改成int类型
        if (this.type == RecordType.RECORD_TYPE_INCREASE){
            return 1;
        }else {
            return 2;
        }
    }

    public void setType(int type) {
        if (type == 1){
            this.type = RecordType.RECORD_TYPE_INCREASE;
        }
        else {
            this.type = RecordType.RECORD_TYPE_DECREASE;
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





}
