package com.cn.message.chapter04.demo03;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.transport.stomp.StompConnection;

import java.io.IOException;

/**
 * @author:Alex
 * @date:2019/11/6
 * @version:1.0
 * @description: 服务器端发送消息
 */
public class StompProducer {
    public static void main(String[] args) throws Exception {
        StompConnection connection = new StompConnection();
        connection.open("localhost",61613);

        // 建立到代理服务器的连接
        connection.connect(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD);
        String message = "<a href=\"https://www.baidu.com\" target=\"_black\">微醺好时光，美酒3件7折，抢购猛戳</a>";

        // 使用STOMP发送消息
        connection.send("/topic/shopping-discount",message);
        connection.disconnect();
        connection.close();


    }
}
