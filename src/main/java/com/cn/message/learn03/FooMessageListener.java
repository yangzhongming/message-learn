package com.cn.message.learn03;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author:Alex
 * @date:2019/10/31
 * @version:1.0
 * @description:
 */
public class FooMessageListener implements MessageListener {
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        System.out.println("接收到消息："+messageBody);
    }
}
