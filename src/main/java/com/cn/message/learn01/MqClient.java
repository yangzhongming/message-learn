package com.cn.message.learn01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author:Alex
 * @date:2019/10/29
 * @version:1.0
 * @description:
 */
public class MqClient {
    // 生产消息
    public static void produce(String message) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 消费消息
    public static String consume(){
        String message = null;
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            // 先向消息队列发送字符串表示消费
            out.println("CONSUME");
            out.flush();
            /* 再从消息队列获取一条消息 */
             message = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
