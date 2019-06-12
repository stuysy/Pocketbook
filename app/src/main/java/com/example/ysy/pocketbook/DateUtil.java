package com.example.ysy.pocketbook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //将unix时间转化为可读的
    public static String getFormattedTime(long timeStamp){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(new Date(timeStamp));
    }
    //转化为2019-06-10
    public static String getFormattedDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }
}
