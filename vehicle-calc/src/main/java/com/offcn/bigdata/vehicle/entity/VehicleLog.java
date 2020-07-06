package com.offcn.bigdata.vehicle.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author deshenglijun
 * @Date 2020/7/6 10:29
 * @Version 1.0
 */
public class VehicleLog implements Serializable{
    private String did;
    private String brand;
    private String vehicleType;
    private String ownership;
    private String industry;

    private String longitude;
    private String latitude;

    private double velocity;

    private String direction;
    private double batteryVoltage;

    private String engineLoad;

    private String avgFuelConsumption;
    private double coolantTemperature;

    private double mileage;

    private int noposition;

    private int commSignal;

    private String runStatus;

    private String driverBehavior;

    private String vehicleAlarm;
    private Long sendTime;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getEngineLoad() {
        return engineLoad;
    }

    public void setEngineLoad(String engineLoad) {
        this.engineLoad = engineLoad;
    }

    public String getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setAvgFuelConsumption(String avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    public double getCoolantTemperature() {
        return coolantTemperature;
    }

    public void setCoolantTemperature(double coolantTemperature) {
        this.coolantTemperature = coolantTemperature;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getNoposition() {
        return noposition;
    }

    public void setNoposition(int noposition) {
        this.noposition = noposition;
    }

    public int getCommSignal() {
        return commSignal;
    }

    public void setCommSignal(int commSignal) {
        this.commSignal = commSignal;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getDriverBehavior() {
        return driverBehavior;
    }

    public void setDriverBehavior(String driverBehavior) {
        this.driverBehavior = driverBehavior;
    }

    public String getVehicleAlarm() {
        return vehicleAlarm;
    }

    public void setVehicleAlarm(String vehicleAlarm) {
        this.vehicleAlarm = vehicleAlarm;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public static VehicleLog json2Bean(String jsonStr) {
        VehicleLog vehicleLog = new VehicleLog();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONObject vjson = jsonObj.getJSONObject("vehicle");
            String did = vjson.getString("did");
            String brand = vjson.getString("brand");
            String vehicleType = vjson.getString("vehicleType");
            String ownership = vjson.getString("ownership");
            String industry = vjson.getString("industry");

            String longitude = jsonObj.getString("longitude");
            String latitude = jsonObj.getString("latitude");
            double velocity = jsonObj.getDouble("velocity");
            String direction = jsonObj.getString("direction");
            double batteryVoltage = jsonObj.getDouble("batteryVoltage");
            String engineLoad = jsonObj.getString("engineLoad");
            String avgFuelConsumption = jsonObj.getString("avgFuelConsumption");
            double coolantTemperature = jsonObj.getDouble("coolantTemperature");
            double mileage = jsonObj.getDouble("mileage");
            int noposition = jsonObj.getInt("noposition");
            int commSignal = jsonObj.getInt("commSignal");
            String runStatus = jsonObj.getString("runStatus");
            String driverBehavior = jsonObj.getString("driverBehavior");
            String vehicleAlarm = jsonObj.getString("vehicleAlarm");
            long sendTime = jsonObj.getLong("sendTime");

            vehicleLog.setDid(did);
            vehicleLog.setBrand(brand);
            vehicleLog.setVehicleType(vehicleType);
            vehicleLog.setOwnership(ownership);
            vehicleLog.setIndustry(industry);

            vehicleLog.setLongitude(longitude);
            vehicleLog.setLatitude(latitude);
            vehicleLog.setVelocity(velocity);
            vehicleLog.setDirection(direction);
            vehicleLog.setBatteryVoltage(batteryVoltage);
            vehicleLog.setEngineLoad(engineLoad);
            vehicleLog.setAvgFuelConsumption(avgFuelConsumption);
            vehicleLog.setCoolantTemperature(coolantTemperature);
            vehicleLog.setMileage(mileage);
            vehicleLog.setNoposition(noposition);
            vehicleLog.setCommSignal(commSignal);
            vehicleLog.setRunStatus(runStatus);
            vehicleLog.setDriverBehavior(driverBehavior);
            vehicleLog.setVehicleAlarm(vehicleAlarm);
            vehicleLog.setSendTime(sendTime);
        } catch (Exception e) {
            return null;
        }
        return vehicleLog;
    }

    @Override
    public String toString() {
        return "VehicleLog{" +
                "did='" + did + '\'' +
                ", brand='" + brand + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", ownership='" + ownership + '\'' +
                ", industry='" + industry + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", velocity=" + velocity +
                ", direction='" + direction + '\'' +
                ", batteryVoltage=" + batteryVoltage +
                ", engineLoad='" + engineLoad + '\'' +
                ", avgFuelConsumption='" + avgFuelConsumption + '\'' +
                ", coolantTemperature=" + coolantTemperature +
                ", mileage=" + mileage +
                ", noposition=" + noposition +
                ", commSignal=" + commSignal +
                ", runStatus='" + runStatus + '\'' +
                ", driverBehavior='" + driverBehavior + '\'' +
                ", vehicleAlarm='" + vehicleAlarm + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
