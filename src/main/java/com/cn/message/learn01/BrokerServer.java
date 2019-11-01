package com.cn.message.learn01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author:Alex
 * @date:2019/10/29
 * @version:1.0
 * @description:
 */
public class BrokerServer implements Runnable {
    public  static int SERVICE_PORT = 9999;
    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true){
                String str = in.readLine();
                if(str==null) {
                    continue;
                }
                System.out.println("接收到原始数据：" + str);
                // 从消息队列中消费一条消息
                if (str.equals("CONSUME")) {
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                } else {
                    // 其他情况都表示生产消息放到消息队列中
                    Broker.prdouce(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(SERVICE_PORT);
            while (true){
                BrokerServer brokerServer = new BrokerServer(server.accept());
                new Thread(brokerServer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
