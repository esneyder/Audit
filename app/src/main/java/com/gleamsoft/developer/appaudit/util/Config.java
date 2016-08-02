package com.gleamsoft.developer.appaudit.util;

import java.text.SimpleDateFormat;
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
}
