/**
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@模块功能：
@修改记录：
@1.
@2.
 */
package com.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtil {

    private final static int DEFAULT_SCLE = 2;

    public static double round(double value, int scale) {
        if (scale < 0) {
            scale = DEFAULT_SCLE;
        }
        BigDecimal bd = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return bd.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 亩2平方米
     * @param value 
     * @param scale
     * @return 
     */
    public static double mu2MSqr(double value, int scale) {
        if (scale < 0) {
            scale = DEFAULT_SCLE;
        }
        BigDecimal bd = new BigDecimal(Double.toString(value));
        BigDecimal db2 = new BigDecimal(Double.toString(666.67D));
        return bd.multiply(db2).doubleValue();
    }

    /**
     * 平方米2亩
     * @param value
     * @param scale
     * @return 
     */
    public static double msqr2Mu(double value, int scale) {
        if (scale < 0) {
            scale = DEFAULT_SCLE;
        }
        BigDecimal bd = new BigDecimal(Double.toString(value));
        BigDecimal db2 = new BigDecimal(Double.toString(666.67D));
        return bd.divide(db2,scale).doubleValue();
    }

    public static BigDecimal round(BigDecimal value, int scale) {
        if (scale < 0) {
            scale = DEFAULT_SCLE;
        }
        BigDecimal one = new BigDecimal("1");
        return value.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static String decimalToText(Object obj, int precision) {
        String result = "";
        if (!(obj instanceof Double || obj instanceof Float || obj instanceof BigDecimal)) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("#.");
        for (int i = 0; i < precision; i++) {
            sb.append("0");
        }

        DecimalFormat df = new DecimalFormat(sb.toString());

        String str = String.valueOf(obj);

        result = df.format(Double.parseDouble(str));

        if (result.length() > 1 && ".".equals(result.substring(0, 1))) {
            result = "0" + result;
        }
        return result.toString();
    }
}
