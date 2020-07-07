package com.offcn.bigdata.vehicle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description 该配置类是springboot中得websocket的配置类
 *   主要为spring容器提供一个对象：ServerEndpointExporter
 *   有了该对象，容器就可以提供websocket的服务了
 * @Author deshenglijun
 * @Date 2020/7/7 9:08
 * @Version 1.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
