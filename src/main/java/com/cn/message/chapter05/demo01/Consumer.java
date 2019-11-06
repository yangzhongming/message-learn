package com.cn.message.chapter05.demo01;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import java.util.List;

/**
 * @author:Alex
 * @date:2019/11/6
 * @version:1.0
 * @description:
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        // 创建一个消费者，并设置一个消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my_consumer_group");
        // 指定NameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        // 设置Consumer第一次启动时从队列头部还是队列尾部开始消费的
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅指定Topic下的所有消息
        consumer.subscribe("topic_example_java","*");
        // 注册消息监听器
        consumer.registerMessageListener((List<MessageExt> list,ConsumeConcurrentlyContext context) -> {
            if (list!=null) {
                for (MessageExt ext: list) {
                    try {
                        System.out.println(new Date()+new String(ext.getBody(),"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("消息消费者已经启动");
    }
}
