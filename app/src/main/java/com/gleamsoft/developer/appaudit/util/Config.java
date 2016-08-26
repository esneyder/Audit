package com.gleamsoft.developer.appaudit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Developer on 1/08/2016.
 */
public class Config {
public static String getDate(){
    Date cDate = new Date();
    String fechaHoy = new SimpleDateFormat("yyyyMMdd").format(cDate);
    return fechaHoy;
}

public static int getHour(){
    int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
return hour;
}
public static int getMinute(){
    int hour = Calendar.getInstance().get(Calendar.MINUTE);
    return hour;
}

public static int getSecunt(){
    int hour = Calendar.getInstance().get(Calendar.SECOND);
    return hour;
}

}
