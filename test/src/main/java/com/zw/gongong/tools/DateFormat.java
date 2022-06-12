package com.zw.gongong.tools;

import java.text.SimpleDateFormat;
import java.util.Date;


/*
    格式化时间的工具类
 */
public class DateFormat {
    /*
    * 返回yyyy-MM-dd HH:mm:ss格式的日期字符串
    * */
    public static String datefor(Date date){
        java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate=format.format(date);
        return nowDate;
    }
}
