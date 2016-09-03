package com.advance.android.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述: 网络连接类
 * 1.网络连接方式
 * 2.网络信号
 * 日期: 2016-09-03
 * 时间: 13:34
 * ——————————————————————————————————
 */
public class AANetUtil {
    public static final int NETWORK_TYPE_DISCONNECT = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;
    public static final int NETWORK_TYPE_WAP = 5;
    public static final int NETWORK_TYPE_UNKNOWN = 6;


    public static final int CHINA_UNKNOWN = 0;//未知
    public static final int CHINA_MOBILE = 1;//移动
    public static final int CHINA_UNICOM = 2;//联通
    public static final int CHINA_TELE = 3;//电信

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].isAvailable()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static int getNetWorkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }

    /**
     * 获取网络类型转换成class int
     *
     * @param context
     * @return
     */
    public static int getNetWorkTypeOject(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        int type = NETWORK_TYPE_DISCONNECT;
        if (manager == null || (networkInfo = manager.getActiveNetworkInfo()) == null) {
            return type;
        }
        if (networkInfo.isConnected()) {
            int mType = networkInfo.getType();
            if (mType == ConnectivityManager.TYPE_WIFI) {
                type = NETWORK_TYPE_WIFI;
            } else if (mType == ConnectivityManager.TYPE_MOBILE) {
                type = getMobileWorkType(context);
            }
        }
        return type;
    }

    /**
     * 获取运营商类型
     * @param context
     * @return
     */
    public static int getProvider(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager == null) {
                return CHINA_UNKNOWN;
            }
            String IMSI = telephonyManager.getSubscriberId();
            if (TextUtils.isEmpty(IMSI)) {
                if (TelephonyManager.SIM_STATE_READY == telephonyManager
                        .getSimState()) {
                    String operator = telephonyManager.getSimOperator();
                    if (!TextUtils.isEmpty(operator)) {
                        if (operator.equals("46000")
                                || operator.equals("46002")
                                || operator.equals("46007")) {
                            return CHINA_MOBILE;
                        } else if (operator.equals("46001")) {
                            return CHINA_UNICOM;
                        } else if (operator.equals("46003")) {
                            return CHINA_TELE;
                        }
                    }
                }
            } else {
                if (IMSI.startsWith("46000") || IMSI.startsWith("46002")
                        || IMSI.startsWith("46007")) {
                    return CHINA_MOBILE;
                } else if (IMSI.startsWith("46001")) {
                    return CHINA_UNICOM;
                } else if (IMSI.startsWith("46003")) {
                    return CHINA_TELE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CHINA_UNKNOWN;
        }

        return CHINA_UNKNOWN;

    }


    private static int getMobileWorkType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_TYPE_2G;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_TYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_TYPE_4G;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return NETWORK_TYPE_UNKNOWN;
            default:
                return NETWORK_TYPE_UNKNOWN;
        }
    }
}
