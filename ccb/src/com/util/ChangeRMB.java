package com.util;

/**
 * @版权：
 * @作者：丁强龙
 * @创建时间:2012年8月21日
 * @模块功能：
 * @修改记录： 1. 2.
 */
public final class ChangeRMB {

    public static String changeToBig(double value) {
        try {
            if (value < 0.01) {
                return "零圆";
            }
            char[] hunit = {'拾', '佰', '仟'};// 段内位置表示 
            char[] vunit = {'万', '亿'}; // 段名表示 
            char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示 
            long midVal = (long) (value * 100); // 转化成整形 
            String valStr = String.valueOf(midVal); // 转化成字符串 
            String head = valStr.substring(0, valStr.length() - 2); // 取整数部分 
            String rail = valStr.substring(valStr.length() - 2); // 取小数部分 

            String prefix = ""; // 整数部分转化的结果 
            String suffix = ""; // 小数部分转化的结果

            if (valStr.length() > 17) {
                return "数值过大！";//解决问题1,超过千亿的问题。
            }


            // 处理小数点后面的数 
            if (rail.equals("00")) { // 如果小数部分为0 
                suffix = "整";
            } else {
                //suffix = digit[rail.charAt(0) - '0'] + "角"
                //        + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来 

                //处理零角,零分发音问题 2010-06-03
                if (rail.charAt(0) == '0') {
                } else {
                    suffix = digit[rail.charAt(0) - '0'] + "角";
                }
                if (rail.charAt(1) == '0') {
                } else {
                    suffix = suffix + digit[rail.charAt(1) - '0'] + "分";
                }
            }
            // 处理小数点前面的数 
            char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组 
            char zero = '0'; // 标志'0'表示出现过0 
            byte zeroSerNum = 0; // 连续出现0的次数 
            for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字 
                int idx = (chDig.length - i - 1) % 4; // 取段内位置 
                int vidx = (chDig.length - i - 1) / 4; // 取段位置 
                if (chDig[i] == '0') { // 如果当前字符是0 
                    zeroSerNum++; // 连续0次数递增 
                    if (zero == '0' && idx != 0) { // 标志 ,连续零，仅读一次零，
                        zero = digit[0];   //解决问题2,当一个零位于第0位时，不输出“零”，仅输出“段名”.
                    } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                        prefix += vunit[vidx - 1];
                        zero = '0';
                    }
                    continue;
                }
                zeroSerNum = 0; // 连续0次数清零 
                if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的 
                    prefix += zero;
                    zero = '0';
                }

                //取到该位对应数组第几位。
                int position = chDig[i] - '0';
                if (position == 1 && i == 0 && idx == 1)//解决问题3 ,即处理10读"拾",而不读"壹拾"
                {
                } else {
                    prefix += digit[position]; // 转化该数字表示 
                }

                if (idx > 0) // 段内位置表示的值
                {
                    prefix += hunit[idx - 1];
                }
                if (idx == 0 && vidx > 0) {      // 段名表示的值 
                    prefix += vunit[vidx - 1];   // 段结束位置应该加上段名如万,亿 
                }
            }

            if (prefix.length() > 0) {
                prefix += '圆'; // 如果整数部分存在,则有圆的字样 
            }
            return prefix + suffix; // 返回正确表示 
        } catch (Exception ex) {
            return "零圆";
        }
    }

    public static void main(String[] args) {
        String tmp = changeToBig(2340912.23);
        System.out.println(tmp);
        tmp = changeToBig(2071234.00);
        System.out.println(tmp);
        tmp = changeToBig(100234.00);
        System.out.println(tmp);
        tmp = changeToBig(100000.00);
        System.out.println(tmp);
        tmp = changeToBig(10000000.00);
        System.out.println(tmp);

        tmp = changeToBig(3500000.00);
        System.out.println(tmp);
        tmp = changeToBig(3500001.00);
        System.out.println(tmp);

        tmp = changeToBig(123500001.00);
        System.out.println(tmp);

        tmp = changeToBig(123500001.11);
        System.out.println(tmp);

        tmp = changeToBig(0);
        System.out.println(tmp);
    }
}
