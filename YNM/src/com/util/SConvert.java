/**
@模块编号：
@模块名称：
@备注 ： 当转换为Shot,int,long时，若返回-1，表示传入的值为null或-1;当转换为float,double,时，若返回0，表示传入的值为null或0；
@模块功能：完成常见系统内置类型的转换
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@修改记录：
1.
2.
 */
package com.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import oracle.sql.TIMESTAMP;

public class SConvert {

    public final static String TRUE = "1";
    public final static String FALSE = "0";
    public final static String TRUE_STRING = "true";
    public final static String FALSE_STRING = "false";

    public static Short toShort(Object value) {
        Short result = -1;
        try {
            result = Short.valueOf(String.valueOf(value));
        } catch (RuntimeException re) {
        }
        return result;
    }

    public static int toInt(Object value) {
        int result = -1;
        try {
            result = Integer.valueOf(String.valueOf(value));
        } catch (RuntimeException re) {
        }
        return result;
    }

    public static long tolong(Object value) {
        long result = Long.MAX_VALUE;
        try {
            result = Long.valueOf(String.valueOf(value));
        } catch (RuntimeException re) {
        }
        return result;
    }

    public static float toFloat(Object value) {
        float result = 0;
        try {
            result = Float.valueOf(String.valueOf(value));
        } catch (RuntimeException re) {
        }
        return result;
    }

    public static double toDouble(Object value) {
        double result = 0;
        try {
            result = Double.valueOf(String.valueOf(value));
        } catch (RuntimeException re) {
        }
        return result;
    }

    /**
     * 
     * @param value
     * @return
     */
    public static String toString(Object value) {
        String result = "";
        try {
            if (value == null) {
                result = null;
            } else {
                result = String.valueOf(value);
            }
        } catch (RuntimeException re) {
        }
        return result;
    }

    /**
     * 该方法需改进
     * @param value
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date toDate(Object value) {
        Date result = null;
        if (value instanceof Date) {
            result = (Date) value;
        } else if (null == value || "".equals(String.valueOf(value))) {
            result = null;
        } else {
            try {
                result = new Date(String.valueOf(value));
            } catch (Exception e) {
                result = null;
            }
        }
        return result;
    }

    /**
     * 字符串转换到时间格式
     * @param dateStr 需要转换的字符串
     * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date toDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 该方法需改进
     * 对Timestamp类型转换
     * @param value
     * @return
     */
    public Timestamp toTimestamp(Object value) {
        Timestamp result = null;
        if (null == value || "".equals(String.valueOf(value))) {
            result = null;
        } else {
            try {
                result = Timestamp.valueOf(String.valueOf(value));
            } catch (Exception e) {
                result = null;
            }
        }
        return result;
    }

    /**
     * 将"true"、"1"转换为true;其他为false
     * @param value
     * @return
     */
    public static boolean toBoolean(Object value) {
        boolean b = false;

        try {
            if (value == null || "".equals(value.toString())) {
                return false;
            }
            String _value = value.toString().trim();
            if (TRUE_STRING.equals(_value.toString().toLowerCase())) {
                b = true;
            } else if (TRUE.equals(_value.toString().toLowerCase())) {
                b = true;
            } else if (Boolean.parseBoolean(_value.toString())) {
                b = true;
            }
        } catch (Exception ex) {
        }
        return b;
    }

    /**
     * 将true,false装换为"1"、"0"
     * @param bool
     * @return
     */
    public static String toBooleanFlag(boolean bool) {
        if (bool) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    /**
     * 将"1"、"0"转换为"true"、"false"
     * @param boolFlag
     * @return
     */
    public static String toBooleanString(String boolFlag) {
        if (TRUE_STRING.equals(boolFlag)) {
            return TRUE_STRING;
        }
        if (FALSE_STRING.equals(boolFlag)) {
            return FALSE_STRING;
        }
        if (TRUE.equals(boolFlag.trim())) {
            return TRUE_STRING;
        } else {
            return FALSE_STRING;
        }
    }

    /**
     * 将"true"、"1"装换为"1";将"false"、"0"装换为"0"
     * @param value
     * @return
     */
    public static String toBooleanFlag(Object value) {
        if (toBoolean(value)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
}
