package com.myr.utils;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    //时间
    public static String getNowTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    //当日日期的拼接  20210128
    public static String getDateNum(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    //给字符 转换成 日期拼接 2021-01-28 20210128
    public static String SringToDateNum(String date){
        int len = date.length();
        String str = "";
        if(len==10){
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            str = year+month+day;
        }
        return str;
    }

    public static void main(String[] args) {
        String s = DateUtils.SringToDateNum("2021-04-23");
        System.out.println(s);
    }
}
