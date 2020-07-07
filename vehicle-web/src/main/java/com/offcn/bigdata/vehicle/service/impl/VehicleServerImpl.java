package com.offcn.bigdata.vehicle.service.impl;

import com.offcn.bigdata.vehicle.common.conf.Constants;
import com.offcn.bigdata.vehicle.common.redis.JedisUtil;
import com.offcn.bigdata.vehicle.service.IVehicleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Description 所有车辆信息的实现类
 * @Author deshenglijun
 * @Date 2020/7/7 9:24
 * @Version 1.0
 *
 * 前面在讲架构的时候，数据从redis中进行加载
 *
 * vehicle.real_time_stat
 *      total ---> 3579
 *      online --> 1245
 *      active ---> 579
 */
@Service
public class VehicleServerImpl implements IVehicleService {
    @Override
    public String totalVehicle() {
        Jedis jedis = JedisUtil.getJedis();

        //从jedis中加载数据
        String total = jedis.hget(Constants.KEY_REAL_TIME_STAT, Constants.FIELD_REAL_TIME_STAT_TOTAL);
        if(StringUtils.isEmpty(total)) {
            total = "0";
        }
        JedisUtil.release(jedis);
        return total;
    }
}
