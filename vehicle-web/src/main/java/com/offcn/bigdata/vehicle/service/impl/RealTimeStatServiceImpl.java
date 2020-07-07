package com.offcn.bigdata.vehicle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.offcn.bigdata.vehicle.common.CommonUtils;
import com.offcn.bigdata.vehicle.common.conf.Constants;
import com.offcn.bigdata.vehicle.common.redis.JedisUtil;
import com.offcn.bigdata.vehicle.service.IRealTimeStatService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @Description TODO
 * @Author deshenglijun
 * @Date 2020/7/7 10:35
 * @Version 1.0
 * 前面在讲架构的时候，数据从redis中进行加载
 *
 * vehicle.real_time_stat
 *      total ---> 3579
 *      online --> 1245
 *      active ---> 579
 */
@Service
public class RealTimeStatServiceImpl implements IRealTimeStatService {

    @Override
    public String statRealTimeInfo() {
        Jedis jedis = JedisUtil.getJedis();

        Map<String, String> map = jedis.hgetAll(Constants.KEY_REAL_TIME_STAT);
        JSONObject json = new JSONObject();
        json.putAll(map);
        int active = Integer.valueOf(map.getOrDefault(Constants.FIELD_REAL_TIME_STAT_ACTIVE, "0"));
        int total = Integer.valueOf(map.get(Constants.FIELD_REAL_TIME_STAT_TOTAL));
        String activeRate = CommonUtils.decimal2Percent(active * 1.0 / total);
        json.put(Constants.FIELD_REAL_TIME_STAT_ACTIVE_RATE, activeRate);

        JedisUtil.release(jedis);
        return json.toString();
    }
}
