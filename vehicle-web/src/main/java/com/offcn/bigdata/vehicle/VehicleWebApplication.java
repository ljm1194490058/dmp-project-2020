package com.offcn.bigdata.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 当前web项目的启动程序
 * @Author deshenglijun
 * @Date 2020/7/7 9:00
 * @Version 1.0
 */
@SpringBootApplication //该注解标识了当前项目就是springboot的项目
public class VehicleWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(VehicleWebApplication.class, args);
    }
}
