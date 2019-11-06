package com.cn.message.chapter04.demo02;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author:Alex
 * @date:2019/11/5
 * @version:1.0
 * @description:
 */
public class Topic1Listener implements MessageListener {
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            try {
                String messageStr = ((TextMessage) message).getText();
                System.out.println("队列监听接收到1的文本消息："+messageStr);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            throw  new IllegalArgumentException("只支持TextMessage类型的消息!");
        }
    }
}
