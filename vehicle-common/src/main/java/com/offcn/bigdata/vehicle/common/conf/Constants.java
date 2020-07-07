package com.offcn.bigdata.vehicle.common.conf;

/**
 * 原始数据字段索引表
 */
public interface Constants {
    String JEDIS_HOST = "jedis.host";
    String JEDIS_PORT = "jedis.port";
    String JEDIS_AUTH = "jedis.auth";

    ///////////////////////////////////////////
    //实时统计数据
    String KEY_REAL_TIME_STAT = "vehicle.real_time_stat";
    String FIELD_REAL_TIME_STAT_RUN = "run";
    String FIELD_REAL_TIME_STAT_TOTAL = "total";
    String FIELD_REAL_TIME_STAT_ONLINE = "online";
    String FIELD_REAL_TIME_STAT_ACTIVE = "active";
    String FIELD_REAL_TIME_STAT_ACTIVE_RATE = "activeRate";
    //行业数据统计
    String KEY_INDUSTRY_CATEGORY = "vehicle.industry_category";
    //车型数据统计
    String KEY_VEHICLE_CATEGORY = "vehicle.vehicle_category";


    //车型颜色数组
    String[] vColors = {"#33b565", "#20cc98", "#2089cf", "#205bcf", "#c3f3c3"};

    //车辆充电高峰时间
    String KEY_CHARGING_TIME_PEAK = "vehicle.charging_time_peak";

    //本月行驶里程TOP
    String KEY_MONTH_MILEAGE_TOP = "vehicle.month_mileage_top";

    //车辆全国区域显示
    String KEY_VEHICLE_PART_MAP = "vehicle.vehicle_part_map";

    //异常行为TOP5
    String KEY_BAD_BEHAVIOR_TOP = "vehicle.bad_behavior_top";

    //报警操作电池
    String KEY_ALARM_EVENT_TOP = "vehicle.alarm_event_top";
}
