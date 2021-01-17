package com.tywh.httpserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��־��¼������
 * @author tywh
 * @version 1.0
 * @since 1.0
 */
public class Logger {
    private Logger() {

    }

    public static void writeLog(String message) {
        System.out.println("[INFO] " + DateUtil.getDate() + " " + message);
    }
}
