package com.tywh.httpserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {

    }

    public static String getDate() {
        return new SimpleDateFormat(Constant.datePattern).format(new Date());
    }
}
