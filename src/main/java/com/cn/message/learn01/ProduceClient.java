package com.cn.message.learn01;

/**
 * @author:Alex
 * @date:2019/10/29
 * @version:1.0
 * @description:
 */
public class ProduceClient {
    public static void main(String[] args) {
        MqClient client = new MqClient();
        client.produce("Hello World...");
    }
}
