package com.offcn.bigdata.vehicle.common.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private ConfigurationManager() {
    }

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(loadStream("default.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static InputStream loadStream(String filename){
        return ConfigurationManager.class.getClassLoader().getResourceAsStream(filename);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntegerProperty(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.valueOf(properties.getProperty(key));
    }

    public static double getDoubleProperty(String key) {
        return Double.valueOf(properties.getProperty(key));
    }
}
