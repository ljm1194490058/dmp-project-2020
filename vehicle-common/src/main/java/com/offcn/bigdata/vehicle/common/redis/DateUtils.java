package com.offcn.bigdata.vehicle.common.redis;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class DateUtils {

    private static FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static String formatDate(Date date) {
        return df.format(date);
    }
    public static String formatDate(long timestamp) {
        return df.format(new Date(timestamp));
    }
}
