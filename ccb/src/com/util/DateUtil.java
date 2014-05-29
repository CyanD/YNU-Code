package com.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @版权：
 * @作者：丁强龙
 * @创建时间:2012年8月21日
 * @模块功能：
 * @修改记录： 1. 2.
 */
public class DateUtil {
	
    
//    private static SimpleDateFormat bigLongSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
    private static SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
    private static SimpleDateFormat daySdf = new SimpleDateFormat("MM");
//    private static SimpleDateFormat hourSdf = new SimpleDateFormat("HH");
//    private static SimpleDateFormat minutesSdf = new SimpleDateFormat("mm");
    private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    private static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static String formatDate(Date myDate, String formatStr) {
        if (myDate == null) {
            return "";
        }
        String strDate = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            strDate = formatter.format(myDate);
        } catch (Exception ex) {
        }
        return strDate;
    }

    public static int getYear() {
    	String strYear = yearSdf.format(new Date());
    	return DVConvert.toInt(strYear);
//        Date dt = new Date();
//        return dt.getYear() + 1900;
    }

    public static int getMonth() {
    	String strYear = monthSdf.format(new Date());
    	return DVConvert.toInt(strYear);
//        Date dt = new Date();
//        return dt.getMonth();
    }

    public static int getDay() {
    	String strYear = daySdf.format(new Date());
    	return DVConvert.toInt(strYear);
//        Date dt = new Date();
//        return dt.getDay();
    }

    /**
     * 
     * @param time1 时间1 例如date1.getTime()
     * @param time2 时间2 例如date2.getTime()
     * @param 要计算的参数如传入Calendar.DATE，则计算天数;Calendar.MONTH则计算月数...
     * @return
     */
    public static long calDifference(long time1, long time2, int field) {
        try {
            if (time1 == time2) {
                return 0;
            } else if (time1 > time2) {
                // 确保time1比time2小   
                return calDifference(time2, time1, field);
            }
            if (field == Calendar.MILLISECOND) {
                long t = time2 - time1;
                return t;
            }
            // 下面清除不要参与比较的内容   
            Calendar cal1 = Calendar.getInstance();
            cal1.setLenient(false);
            cal1.setTimeInMillis(time1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setLenient(false);
            cal2.setTimeInMillis(time2);
            for (int x = 0; x < Calendar.FIELD_COUNT; x++) {
                if (x > field) {
                    cal1.clear(x);
                    cal2.clear(x);
                }
            }
            // 重新设定初始值   
            time1 = cal1.getTimeInMillis();
            time2 = cal2.getTimeInMillis();
            long ms = 0;
            int min = 0;// 下限,从0开始   
            int max = 1;// 每次所加的值,第一次加1   
            while (true) {
                // 因为max的值都是相对time1而言,故每次都是从time1开始而不是ms   
                cal1.setTimeInMillis(time1);
                cal1.add(field, max);// 将field对应的字段加上max的值   
                ms = cal1.getTimeInMillis();
                if (ms == time2) {
                    // 两个时间之间相差的值为max   
                    min = max;
                    break;
                } else if (ms > time2) {
                    // 超过后,则差值肯定小于max   
                    break;
                } else {
                    // 仍然小于time2,继续增大max,直到ms>=time2为止   
                    max <<= 1;
                }
            }
            // 上面的操作中没有找到准确的值,接下来使用二分查找以准确找出差值   
            while (max > min) {
                cal1.setTimeInMillis(time1);
                int t = (min + max) >>> 1;
                cal1.add(field, t);
                ms = cal1.getTimeInMillis();
                if (ms == time2) {
                    min = t;
                    break;
                } else if (ms > time2) {
                    max = t;
                } else {
                    min = t;
                }
            }
            return min;
        } catch (Exception ex) {
            return 0L;
        }
    }

    /**
     * 
     * @param value
     * @param days 传入的是正值为+，负值为1
     * @return
     */
    public static Date addDays(Date value, int days) {
        Date date = value;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 
     * @param value
     * @param format
     * @return
     */
    public static Date toDate(String value, String format) {
        SimpleDateFormat d2 = new SimpleDateFormat(format);
        try {
            return d2.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @param value 
     * @param format 
     * @return
     */
    public static String toFormatString(Date value, String format) {
        SimpleDateFormat d2 = new SimpleDateFormat(format);
        return d2.format(value);
    }

    public static String getNowString() {
        Timestamp tnow = new Timestamp(System.currentTimeMillis());
        return tnow.toString();
    }
    public static Timestamp getNowTsp() {
        return  new Timestamp(System.currentTimeMillis());
    }


    /**
     * 获得本周的第一天，即周日
     *
     * @return
     */
    public static Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的最后一天，即本周六
     *
     * @return
     */
    public static Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 7 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本天的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentDayStartTime() {
        Date now = new Date();
        try {
            now = shortSdf.parse(shortSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本天的结束时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentDayEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的开始时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourStartTime() {
        Date now = new Date();
        try {
            now = longHourSdf.parse(longHourSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的结束时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(longHourSdf.format(now) + ":59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return now;
    }
}