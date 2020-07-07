package com.offcn.bigdata.vehicle.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 公用的工具类
 */
public class CommonUtils {

    /**
     * 通过四舍五入保留n为小数
     * @param d 被处理的小数
     * @param scale 保留几位
     * @return
     */
    public static double decimal(double d, int scale) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String decimal2Percent(double d) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(d);
    }


    public static void main(String[] args) {
        System.out.println(decimal(69.295, 2));//69.3

        System.out.println(decimal2Percent(0.12345));
    }

    public static int hour(long timestamp) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timestamp);
        return calendar.get(Calendar.HOUR);
    }
}
