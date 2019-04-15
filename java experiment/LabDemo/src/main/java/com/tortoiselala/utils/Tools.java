package com.tortoiselala.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    /**
     * 常规格式化时间方式
     */
    private static String NORMAL_FORMAT = "yy-MM-dd HH-mm-ss";


    /**
     * 获取当前时间的格式化字符串
     */
    public static String getFormatedDate(){
        return getFormatedDate(new Date());
    }

    public static String getFormatedDate(Date date){
        return new SimpleDateFormat(NORMAL_FORMAT).format(date);
    }
}
