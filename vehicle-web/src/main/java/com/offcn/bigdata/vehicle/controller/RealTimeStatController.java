package com.offcn.bigdata.vehicle.controller;

import com.offcn.bigdata.vehicle.service.IRealTimeStatService;
import com.offcn.bigdata.vehicle.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Description 主要进行所有车辆信息和页面交互的mvc controller
 * 成为一个websocket的controller类，需要编写四个核心的方式
 * onMessage，onOpen，onError，onClose，为了能够让容器能够识别是websocket的核心方法
 * 需要添加对应的注解：@OnMessage，@OnOpen， @OnError， @OnClose
 * @Author deshenglijun
 * @Date 2020/7/7 9:12
 * @Version 1.0
 */
@Controller
@ServerEndpoint("/visualize/real_time_stat")
public class RealTimeStatController {

    private static IRealTimeStatService realTimeStatService;

    @OnMessage
    public void onMessage(String messages, Session session)
            throws IOException, InterruptedException {
        while (true) {
            String realTimeInfo = realTimeStatService.statRealTimeInfo();
            System.out.println("realTimeInfo===>" + realTimeInfo);
            //服务端向客户端发送数据
            session.getBasicRemote().sendText(realTimeInfo);
            Thread.sleep(1000);
        }
    }
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    //客户端和服务端建立起连接之后就会回调该方法
    @OnOpen
    public void onOpen(){
        System.out.println("real_time_stat:连接到服务了*************");
    }
    @OnClose
    public void onClose(){
        System.out.println("Connection closed....");
    }

    //静态注入
    @Autowired
    public void setRealTimeStatService(IRealTimeStatService realTimeStatService) {
        RealTimeStatController.realTimeStatService = realTimeStatService;
    }
}
