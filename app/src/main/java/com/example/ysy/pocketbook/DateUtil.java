package com.example.ysy.pocketbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //将unix时间转化为可读的
    public static String getFormattedTime(long timeStamp){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(new Date(timeStamp));//返回格式化时间
    }
    //转化为2019-06-10
    public static String getFormattedDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    //解析日期
    private static Date strToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    //返回星期几
    public static String getWeekDay(String date){
        String[] weekdays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int index = calendar.get(Calendar.DAY_OF_WEEK)-1;
        return  weekdays[index];
    }

    //返回月份
    public static String getDateTitle(String date){
        String[] months ={"January", "February", "March", "April", "May", "June","July", "August","September","October", "November", "December"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int monthIndex = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return  months[monthIndex] + " "+String.valueOf(day); //返回 June 10
    }
}
