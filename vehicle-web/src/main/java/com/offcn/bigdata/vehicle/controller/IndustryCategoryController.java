package com.offcn.bigdata.vehicle.controller;

import com.offcn.bigdata.vehicle.service.IIndustryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Description TODO
 * @Author deshenglijun
 * @Date 2020/7/7 11:02
 * @Version 1.0
 */
@Controller
@ServerEndpoint("/visualize/industryCategory")
public class IndustryCategoryController {

    private static IIndustryCategoryService industryCategoryService;

    @OnMessage
    public void onMessage(String messages, Session session)
            throws IOException, InterruptedException {
        while (true) {
            String industryCategory = industryCategoryService.fetchIndustryCategory();
            System.out.println("industryCategory===>" + industryCategory);
            //服务端向客户端发送数据
            session.getBasicRemote().sendText(industryCategory);
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
        System.out.println("industryCategory:连接到服务了*************");
    }
    @OnClose
    public void onClose(){
        System.out.println("Connection closed....");
    }

    //静态注入
    @Autowired
    public void setIndustryCategoryService(IIndustryCategoryService industryCategoryService) {
        IndustryCategoryController.industryCategoryService = industryCategoryService;
    }
}
