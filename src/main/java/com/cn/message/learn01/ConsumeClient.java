package com.cn.message.learn01;

/**
 * @author:Alex
 * @date:2019/10/29
 * @version:1.0
 * @description:
 */
public class ConsumeClient {
    public static void main(String[] args) {
        MqClient client = new MqClient();
        String message = client.consume();
        System.out.println("获取的消息为："+message);
    }
}
