package com.advance.android.sdk.utils;

import android.print.PageRange;
import android.text.TextUtils;
import android.util.Log;

import org.w3c.dom.Text;

/**
 * TODO 使用工厂模式
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述: Log工具类
 * 日期: 2016-09-03
 * 时间: 13:32
 * ——————————————————————————————————
 */
public class AALog {
    private static final int METHOD_V = 1;
    private static final int METHOD_W = 2;
    private static final int METHOD_D = 3;
    private static final int METHOD_E = 4;
    /**
     * 是否开启log
     */
    private static boolean debug = true;
    /**
     * 是否包含所有信息
     */
    private static boolean logInfo = true;

    public static void v(String tag, String message) {
        showLog(tag, message, METHOD_V);
    }

    public static void w(String tag, String message) {
        showLog(tag, message, METHOD_W);
    }

    public static void d(String tag, String message) {
        showLog(tag, message, METHOD_D);
    }

    public static void e(String tag, String message) {
        showLog(tag, message, METHOD_E);
    }

    private static void showLog(String tag, String message, int method) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(message)) return;
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            switch (method) {
                case METHOD_V:
                    Log.v(tag, message + getLogInfo(stackTraceElement));
                    break;
                case METHOD_D:
                    Log.d(tag, message + getLogInfo(stackTraceElement));
                    break;
                case METHOD_E:
                    Log.e(tag, message + getLogInfo(stackTraceElement));
                    break;
                case METHOD_W:
                    Log.w(tag, message + getLogInfo(stackTraceElement));
                    break;

            }
        }
    }


    public static void setDebug(boolean debug) {
        AALog.debug = debug;
    }

    public static void setLogInfo(boolean logInfo) {
        AALog.logInfo = logInfo;
    }

    /**
     * 获取常用Log信息
     *
     * @param stackTraceElement
     * @return
     */
    private static String getLogInfo(StackTraceElement stackTraceElement) {
        if (!logInfo || stackTraceElement == null) return "";
        StringBuilder builder = new StringBuilder();
        String threadName = Thread.currentThread().getName();
        long threadId = Thread.currentThread().getId();
        if (!TextUtils.isEmpty(threadName)) {
            builder.append("====[ ThreadName:" + threadName);
        }
        builder.append(" ThreadId:" + threadId);
        builder.append(" ====");
        return builder.toString();
    }


}
