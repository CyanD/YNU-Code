/**
 * @版权：
 * @作者：丁强龙
 * @创建时间:2012年8月21日
 * @模块功能：
 * @修改记录： 1. 2.
 */
package com.util;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.commons.lang.StringUtils;

public class StringUtil {

    final static String[] NeddFilt = {"and", "select", "from", "'", "union", "as", "drop", "table", "char", "str", ">", "<", "nvl", "length", "substr",
        "exec", "insert", "delete", "update", "count", "*", "%", "chr", "mid", "ascii",
        "master", "truncate", "declare", ";", "or", ",", "=", "exists", "len", "where", "substring", "admin",
        ","};

    public static void main(String[] args) {

        double d1 = 67.0;

        String s2 = Double2StrE(d1, 2);
        String sFilt = "L";
        String sF1 = GetAndFilt(sFilt);
        String sF2 = GetAndF1Str(sFilt);
        String sF3 = GetAndF1Str(sFilt, 2);

        //String s = parse("<   type=\"text\" name=\"F_name\" id=\"F_name\" value=\"kelly\" min=\"0\" max=\"31\" readonly=\"readonly\" zh_name=\"姓名\" >");
        //System.out.println(s);
    }

    public static String stuffString(Object obj, int len, String fillStr) {
        String str = toString(obj);
        if (str.length() > len) {
            return str;
        }
        while (str.length() < len) {
            str = fillStr + str;
        }
        return str;

    }

    public static String GetAndFilt(String param) {
        String r = "";
        if (param == null || "".equals(param)) {
            return r;
        }
        //if(param.length()<=1)return param;

        String str2 = param.toLowerCase();
        //if(NeddFilt==null)return str2.indexOf("select")>=0?"":param;

//        for(int i=0;i<NeddFilt.length;i++){
//            if(str2.indexOf(NeddFilt[i])>=0){
//                param="";//set blank
//                break;
//            }
//        }

        //D20120515修改
        String regx = " |,|\\(|\\;|\\)|\\r|\\n|\\t";//(->u0028,)->u0029
        String[] tps = str2.split(regx);
        if (tps != null && tps.length > 0) {
            for (int i = 0; i < NeddFilt.length; i++) {
                for (int j = 0; j < tps.length; j++) {
                    if (NeddFilt[i].equals(tps[j])) {
                        param = "";//set blank
                        break;
                    }
                }
            }
        }

        return param;
    }

    public static String GetAndF1Str(String param) {
        return GetAndF1Str(param, 1);
    }

    public static String GetAndF1Str(String param, int len) {
        param = GetAndFilt(param);
        if (param == null) {
            return "";
        }
        if (param.length() >= len) {
            return param.substring(0, len);
        }
        return "";
    }

    public static String lpad(int num, int length, char ch) {

        String str = Integer.toString(num);
        int len = str.length();
        for (; len < length;) {
            str = ch + str;
            len = str.length();
        }
        return str;
    }

    public static String lpad(String _str, int length, char ch) {

        String str = _str;
        int len = str.length();
        for (; len < length;) {
            str = ch + str;
            len = str.length();
        }
        return str;
    }

    public static String rpad(String _str, int length, char ch) {

        String str = _str;
        int len = str.length();
        for (; len < length;) {
            str = str + ch;
            len = str.length();
        }
        return str;
    }

    public static String getStr(String str) {
        if (str == null) {
            return null;
        }
        try {
            String temp_p = str;
            byte[] temp_b = temp_p.getBytes("iso-8859-1");
            String temp = new String(temp_b);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String escapeNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String escapeHTMLTags(String input) {
        if ((input == null) || (input.length() == 0)) {
            return "";
        }
        if ("null".equals(input)) {
            return "";
        }

        StringBuffer buf = new StringBuffer(input.length() + 6);
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '"') {
                buf.append("&quot;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    public static int[] StrToInt(String[] asTmp) {
        int[] aiTmp = new int[asTmp.length];
        for (int i = 0; i < aiTmp.length; i++) {
            aiTmp[i] = Integer.parseInt(asTmp[i]);
        }
        return aiTmp;
    }

    public static long[] StrToLong(String[] asTmp) {
        long[] aiTmp = new long[asTmp.length];
        for (int i = 0; i < aiTmp.length; i++) {
            aiTmp[i] = Long.parseLong(asTmp[i]);
        }
        return aiTmp;
    }

    public static String[] distinct(String[] asTmp) {
        String str = "";
        boolean isExist = false;
        for (int i = 0; i < asTmp.length; i++) {
            isExist = false;
            for (int j = 0; j < asTmp.length; j++) {
                if ((i < j) && (asTmp[i].equals(asTmp[j]))) {
                    str = str + asTmp[i] + ",";
                    isExist = true;
                    break;
                }
                if ((i > j) && (asTmp[i].equals(asTmp[j]))) {
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                str = str + asTmp[i] + ",";
            }
        }
        return str.split(",");
    }

    public static String interceptSTR(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() > 25) {
            return str.substring(0, 25) + "......";
        }
        return str;
    }

    public static String getDateTimeStr(String strDateTime) {
        if ((strDateTime == null) || (strDateTime.equals("")) || (strDateTime.equals("null"))) {
            return "";
        }
        String[] astmp = strDateTime.split(" ");

        return astmp[0] + " " + astmp[1].substring(0, 5);
    }

    public static String getDateStr_zh(String strDateTime) {
        if ((strDateTime == null) || (strDateTime.equals("")) || (strDateTime.equals("null"))) {
            return "";
        }
        String[] astmp = strDateTime.split(" ");

        String[] astmp1 = astmp[0].split("-");
        return astmp1[0] + "年" + astmp1[1] + "月" + astmp1[2] + "日";
    }

    public static String getDateTimeStr_zh(String strDateTime) {
        if ((strDateTime == null) || (strDateTime.equals("")) || (strDateTime.equals("null"))) {
            return "";
        }
        String[] astmp = strDateTime.split(" ");

        String[] astmp1 = astmp[0].split("-");

        String[] astmp2 = astmp[1].split(":");
        return astmp1[0] + "年" + astmp1[1] + "月" + astmp1[2] + "日" + astmp2[0] + "时" + astmp2[1] + "分";
    }

    public static String getDateStr(String strDateTime) {
        if ((strDateTime == null) || (strDateTime.equals("")) || (strDateTime.equals("null"))) {
            return "";
        }
        String[] astmp = strDateTime.split(" ");

        return astmp[0];
    }

    public static String getTimeStr(String strFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        String strDate = formatter.format(new Date());
        return strDate;
    }

    public static String getTimeStampStr() {
        return getTimeStr("yyyy-MM-dd HH:mm:ss");
    }

    public static String formatPrn(String str) {
        char[] acStr = str.toCharArray();
        for (int i = 0; i < acStr.length; i++) {
            if ((acStr[i] == '\r') || (acStr[i] == '\n')) {
                acStr[i] = '\000';
            }
        }
        return String.copyValueOf(acStr);
    }

    public static String getFileExt(String filename) {
        int pos = 0;
        String s = filename;
        String ext = "";
        while (!s.equals("")) {
            pos = s.indexOf(".");
            if (pos < 0) {
                s = "";
                continue;
            }

            ext = s.substring(pos);
            s = s.substring(pos + 1);
        }

        return ext;
    }

    public static int radom() {
        Random ran = new Random();
        return ran.nextInt();
    }

    public static String getDateStr_CN(String strDate) {
        if ((strDate == null) || (strDate.equals(""))) {
            return strDate;
        }
        String[] astmp = strDate.split(" ");

        String[] astmp1 = astmp[0].split("-");

        String tmp = "";
        char[] acTmp = astmp1[0].toCharArray();
        for (int i = 0; i < acTmp.length; i++) {
            switch (acTmp[i]) {
                case '1':
                    tmp = tmp + "一";
                    break;
                case '2':
                    tmp = tmp + "二";
                    break;
                case '3':
                    tmp = tmp + "三";
                    break;
                case '4':
                    tmp = tmp + "四";
                    break;
                case '5':
                    tmp = tmp + "五";
                    break;
                case '6':
                    tmp = tmp + "六";
                    break;
                case '7':
                    tmp = tmp + "七";
                    break;
                case '8':
                    tmp = tmp + "八";
                    break;
                case '9':
                    tmp = tmp + "九";
                    break;
                case '0':
                    tmp = tmp + "○";
            }
        }

        tmp = tmp + "年";
        int a = Integer.parseInt(astmp1[1]);
        switch (a) {
            case 1:
                tmp = tmp + "一";
                break;
            case 2:
                tmp = tmp + "二";
                break;
            case 3:
                tmp = tmp + "三";
                break;
            case 4:
                tmp = tmp + "四";
                break;
            case 5:
                tmp = tmp + "五";
                break;
            case 6:
                tmp = tmp + "六";
                break;
            case 7:
                tmp = tmp + "七";
                break;
            case 8:
                tmp = tmp + "八";
                break;
            case 9:
                tmp = tmp + "九";
                break;
            case 10:
                tmp = tmp + "十";
                break;
            case 11:
                tmp = tmp + "十一";
                break;
            case 12:
                tmp = tmp + "十二";
        }

        tmp = tmp + "月";
        int b = Integer.parseInt(astmp1[2]);

        if ((b > 9) && (b < 20)) {
            tmp = tmp + "十";
            b -= 10;
        }
        if ((b > 19) && (b < 30)) {
            tmp = tmp + "二十";
            b -= 20;
        }
        if (b > 29) {
            tmp = tmp + "三十";
            b -= 30;
        }
        switch (b) {
            case 1:
                tmp = tmp + "一";
                break;
            case 2:
                tmp = tmp + "二";
                break;
            case 3:
                tmp = tmp + "三";
                break;
            case 4:
                tmp = tmp + "四";
                break;
            case 5:
                tmp = tmp + "五";
                break;
            case 6:
                tmp = tmp + "六";
                break;
            case 7:
                tmp = tmp + "七";
                break;
            case 8:
                tmp = tmp + "八";
                break;
            case 9:
                tmp = tmp + "九";
                break;
            case 0:
                tmp = tmp + "";
        }

        tmp = tmp + "日";
        return tmp;
    }

    public static String getNum_zh(int n) {
        return "";
    }

    public static String getNum_cn(int n) {
        String str = "" + n;
        char[] actmp = str.toCharArray();
        int j = actmp.length;
        for (int i = 0; i < actmp.length; i++);
        return "";
    }

    public static String DoubleToStr(double d) {
        return DoubleToStr(d, 2);
    }

    public static String DoubleToStr(double d, int Point) {
        //BigDecimal bb = new BigDecimal(d);
        //BigDecimal cc = bb.setScale(Point, 1);
        //return cc.toString();
        return MathUtil.decimalToText(d, Point);
        //return numPrise(d, Point);
    }

    public static String Double2StrE(Number num, int bits) {
        String s = "0.0";
        String sr = "";
        if (null != num) {
            DecimalFormat df = new DecimalFormat("#.00000000");
            s = df.format(num);
            s = StringUtils.stripEnd(s, "0");
        }
        sr = s;
        int pos = s.indexOf(".");
        if (pos >= 0) {
            String s1 = s.substring(0, pos);
            String s2 = s.substring(pos + 1);
            //System.out.println(s2);
            if (s2.length() < bits) {
                while (s2.length() < bits) {
                    s2 = s2 + "0";
                }

            }
            if (s1.equals("")) {
                s1 = "0";
            }
            sr = s1 + "." + s2;
            //else {
            //    s2 = s2.substring(0, bits);
            //}

        } else {

            String s2 = "";
            while (s2.length() < bits) {
                s2 = s2 + "0";
            }
            sr = s + "." + s2;
        }
        return sr;
    }

    public static String numPrise(Number num, int bits) {
        String sr = "";
        if (null != num) {
            DecimalFormat df = new DecimalFormat("#.00000000");
            String s = df.format(num);
            //System.out.println(s);
            //s="12345";
            int pos = s.indexOf(".");
            if (pos > 0) {
                String s1 = s.substring(0, pos);
                String s2 = s.substring(pos + 1);
                //System.out.println(s2);
                if (s2.length() < bits) {
                    while (s2.length() < bits) {
                        s2 = s2 + "0";
                    }
                } else {
                    s2 = s2.substring(0, bits);
                }
                sr = s1 + "." + s2;
            } else {

                String s2 = "";
                while (s2.length() < bits) {
                    s2 = s2 + "0";
                }
                sr = s + "." + s2;
            }

            return sr;
        } else {
            return "";
        }
    }

    public static String convert(double value) {
        DecimalFormat formatter = new DecimalFormat("###,###.00");
        return formatter.format(value);
    }

    public static String ISO2UTF(String str) {
        if (str == null) {
            return null;
        }
        try {
            String temp_p = new String(str.getBytes("ISO8859-1"), "UTF-8");
            return temp_p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将传入类型转换为String
     * @param value
     * @return 
     */
    public static String toString(Object value) {
        return value == null ? "" : value.toString();
    }

    /**
     * 将传入类型转换为String
     * @param value
     * @return 
     */
    public static String toStringEsNull(Object value) {
        return (value == null || "null".equals(value)) ? "" : value.toString();
    }

    /**
     * 
     * @param value
     * @return 
     */
    public static int toInt(Object value) {
        try {
            String str = value == null ? "" : value.toString();
            return Integer.parseInt(str);
        } catch (Exception ex) {
        }
        return -1;
    }

    /**
     * 
     * @param value
     * @return 
     */
    public static long toLong(Object value) {
        try {
            String str = value == null ? "" : value.toString();
            return Long.parseLong(str);
        } catch (Exception ex) {
        }
        return -1l;

    }

    public static double toDouble(Object value) {
        String str = value == null ? "" : value.toString();
        double d = -1;
        try {
            d = Double.parseDouble(str);
        } catch (Exception ex) {
        }
        return d;
    }

    public static int toInt0(Object value) {
        try {
            String str = value == null ? "" : value.toString();
            return Integer.parseInt(str);
        } catch (Exception ex) {
        }
        return 0;
    }

    /**
     * 
     * @param value
     * @return 
     */
    public static long toLong0(Object value) {
        try {
            String str = value == null ? "" : value.toString();
            return Long.parseLong(str);
        } catch (Exception ex) {
        }
        return 0l;

    }

    public static double toDouble0(Object value) {
        String str = value == null ? "" : value.toString();
        double d = 0;
        try {
            d = Double.parseDouble(str);
        } catch (Exception ex) {
        }
        return d;
    }
}