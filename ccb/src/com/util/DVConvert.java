/**
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@模块功能：常用的日期函数,基于java.util.Date
@修改记录：
@1.
@2.
@备注 ：当传入对象为null,返回默认值，long\int\short默认值是0;String默认值是"";float,double为0.0
 */
package com.util;

public class DVConvert {

    public final static String DEFAULT_STRING = "";
    public final static Short DEFAULT_SHORT = 0;
    public final static int DEFAULT_INT = 0;
    public final static long DEFAULT_LONG = 0;
    public final static float DEFAULT_FLOAT = 0.0f;
    public final static double DEFAULT_DOUBLE = 0.0d;

    public static Short toShort(Object value) {
        Short result = SConvert.toShort(value);
        if (result.shortValue() == -1) {
            result = DEFAULT_SHORT;
        }
        return result;
    }

    public static int toInt(Object value) {
        int result = SConvert.toInt(value);
        if (result == -1) {
            result = DEFAULT_INT;
        }
        return result;
    }

    public static long tolong(Object value) {
        long result = SConvert.tolong(value);
        if (result == -1) {
            result = DEFAULT_LONG;
        }
        return result;
    }

    public static float toFloat(Object value) {
        if (value == null) {
            return DEFAULT_FLOAT;
        }
        float result = SConvert.toFloat(value);
        if (result == 0) {
            result = DEFAULT_FLOAT;
        }
        return result;
    }

    public static double toDouble(Object value) {
        if (value == null) {
            return DEFAULT_DOUBLE;
        }
        double result = SConvert.toDouble(value);
        if (result == 0) {
            result = DEFAULT_DOUBLE;
        }
        return result;
    }

    /**
     * 
     * @param value
     * @return 当value为null时返回""
     */
    public static String toString(Object value) {
        String result = SConvert.toString(value);
        if (result == null) {
            result = DEFAULT_STRING;
        }
        return result;
    }

    public static String toString(Object value, String blankReVal) {
        String result = SConvert.toString(value);
        if (result == null) {
            result = DEFAULT_STRING;
        }
        if (result == null || "".equals(blankReVal) || "".equals(result.trim())) {
            result = blankReVal;
        }
        return result;
    }
}
