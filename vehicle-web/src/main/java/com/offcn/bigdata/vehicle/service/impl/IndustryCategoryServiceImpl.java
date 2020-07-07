package com.offcn.bigdata.vehicle.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.offcn.bigdata.vehicle.common.conf.Constants;
import com.offcn.bigdata.vehicle.common.redis.JedisUtil;
import com.offcn.bigdata.vehicle.service.IIndustryCategoryService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * @Description 每一个行业及其对应的车辆的多少
 *  使用hash的数据结构是最好的
 *  {
 *      "data": [
 *          {value: 42, name: '行业一'},
            {value: 23, name: '行业二'},
            {value: 70, name: '行业三'},
            ...
 *      ]
 *      "category": ["行业一", "行业二", "行业三",...]
 *  }
 * @Author deshenglijun
 * @Date 2020/7/7 11:15
 * @Version 1.0
 */
@Service
public class IndustryCategoryServiceImpl implements IIndustryCategoryService {
    @Override
    public String fetchIndustryCategory() {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> iCategoryMap = jedis.hgetAll(Constants.KEY_INDUSTRY_CATEGORY);
        JSONObject jsonObj = new JSONObject();

        Set<String> keys = iCategoryMap.keySet();
        JSONArray iCategoryArr = new JSONArray();
        iCategoryArr.addAll(keys);
        jsonObj.put("category", iCategoryArr);

        JSONArray dateArray = new JSONArray();
        for (String key : keys) {
            JSONObject obj = new JSONObject();
            obj.put("name", key);
            obj.put("value", iCategoryMap.get(key));
            dateArray.add(obj);
        }
        jsonObj.put("data", dateArray);

        JedisUtil.release(jedis);
        return jsonObj.toJSONString();
    }
}
