package com.horen.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/14:54
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class DateUtil {
    private static final int DAY = 86400;
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE1_TIME = "yyyy/MM/dd HH:mm";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_TIME_SECOND = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT_MONTH_DAY = "MM月dd日";
    public static final String FORMAT_MONTH_DAY_TIME = "MM月dd日  hh:mm";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_YEAR = "yyyy";
    private static final int HOUR = 3600;
    private static final int MINUTE = 60;
    private static final int MONTH = 2592000;
    private static final int YEAR = 31536000;
    private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static String unix2time(long timestamp, String formatType) {
        return new SimpleDateFormat(formatType).format(new Date(1000 * timestamp));
    }

    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long timeGap = (System.currentTimeMillis() - timestamp) / 1000;
        if (timeGap > 31536000) {
            return (timeGap / 31536000) + "年前";
        }
        if (timeGap > 2592000) {
            return (timeGap / 2592000) + "个月前";
        }
        if (timeGap > 86400) {
            return (timeGap / 86400) + "天前";
        }
        if (timeGap > 3600) {
            return (timeGap / 3600) + "小时前";
        }
        if (timeGap > 60) {
            return (timeGap / 60) + "分钟前";
        }
        return "刚刚";
    }

    public static String getTimeFromTimestamp(long timestamp) {
        long timeGap = (System.currentTimeMillis() - timestamp) / 1000;
        if (timeGap > 86400 && timestamp < 172800) {
            return "昨天";
        }
        if (timeGap < 86400) {
            return "今天";
        }
        return "更早";
    }

    public static String getCurrentTime(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern("yyyy-MM-dd HH:mm");
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    public static String longToString(long currentTime, String formatType) {
        return dateToString(longToDate(currentTime, formatType), formatType);
    }

    public static Date stringToDate(String strTime, String formatType) {
        Date date = null;
        try {
            date = new SimpleDateFormat(formatType).parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date longToDate(long currentTime, String formatType) {
        return stringToDate(dateToString(new Date(currentTime), formatType), formatType);
    }

    public static long stringToLong(String strTime, String formatType) {
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        }
        return dateToLong(date);
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static String date2unixTime(String currentDate) {
        String currentUnixTime = null;
        try {
            currentUnixTime = String.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(currentDate).getTime()).substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentUnixTime;
    }

    public static long getToday() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return new Date(((date.getTime() - ((long) (((gc.get(11) * 60) * 60) * 1000))) - ((long) ((gc.get(12) * 60) * 1000))) - ((long) (gc.get(13) * 1000))).getTime() / 1000;
    }

    public static long getYesToday() {
        return getToday() - 86400;
    }

    public static String translateTime(int time) {
        if (time <= 60) {
            return time + "分钟";
        }
        return (time / 60) + "小时" + (time % 60) + "分钟";
    }
}
