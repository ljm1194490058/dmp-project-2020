package com.offcn.bigdata.vehicle.common.redis;

import com.offcn.bigdata.vehicle.common.PropertiesUtil;
import com.offcn.bigdata.vehicle.common.conf.ConfigurationManager;
import com.offcn.bigdata.vehicle.common.conf.Constants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * redis的工具类
 */
public class JedisUtil {
    private JedisUtil(){}

    private static JedisPool pool;
    static {
        JedisPoolConfig jedisConfig = new JedisPoolConfig();
        String host = ConfigurationManager.getProperty(Constants.JEDIS_HOST);
        int port = ConfigurationManager.getIntegerProperty(Constants.JEDIS_PORT);
        pool = new JedisPool(jedisConfig, host, port);
    }
    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void release(Jedis jedis) {
        jedis.close();
    }

    public static String getJedisProperty(String field) {
        return PropertiesUtil.getStringByKey("default.properties", field);
    }
    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> map = new HashMap<>();
        map.put("SUV", 132 + "");
        map.put("MPV", 162 + "");
        map.put("跑车", 162 + "");
        map.put("新能源", 292 + "");

        jedis.hmset(Constants.KEY_VEHICLE_CATEGORY, map);
        JedisUtil.release(jedis);
        System.out.println("ok");
    }
}
