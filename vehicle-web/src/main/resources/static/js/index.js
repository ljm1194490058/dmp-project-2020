function loadInit() {
    // 基于准备好的dom，初始化echarts图表
    loadRealTimeAllVehicles();//实时行驶车辆(辆)
    loadVehiclePartMap();//各地车辆区域分布
    loadRealTimeStat();//实时统计
    loadIndustryCategory();//行业分类
    loadVehicleCategory();//车型分类
    loadChargingTimePeak(); //车辆充电高峰时间
    loadMonthMileageTop();//本月行驶里程TOP5
    loadWarnVehicleTop();//报警车辆TOP5
    loadWarnChargeTop();//电池报警车辆TOP10
}