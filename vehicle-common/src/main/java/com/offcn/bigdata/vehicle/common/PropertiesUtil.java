package com.offcn.bigdata.vehicle.common;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description TODO
 * @Author deshenglijun
 * @Date 2020/5/30 19:37
 * @Version 1.0
 */
public class PropertiesUtil {

    public static String getStringByKey(String filename, String key) {
        Properties prop = loadProperties(filename);
        return prop.getProperty(key);
    }

    public static Properties loadProperties(String filename) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
