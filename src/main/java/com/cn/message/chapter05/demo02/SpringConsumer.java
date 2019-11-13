package com.cn.message.chapter05.demo02;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * @author:Alex
 * @date:2019/11/13
 * @version:1.0
 * @description:
 */
public class SpringConsumer {
    private Logger logger = Logger.getLogger(getClass());
    private String consumerGroupName;
    private String nameServerAddr;
    private String topicName;
    private DefaultMQPushConsumer consumer;
    private MessageListenerConcurrently messageListenerConcurrently;


    public SpringConsumer(String consumerGroupName, String nameServerAddr, String topicName, MessageListenerConcurrently messageListenerConcurrently) {
        this.consumerGroupName = consumerGroupName;
        this.nameServerAddr = nameServerAddr;
        this.topicName = topicName;
        this.messageListenerConcurrently = messageListenerConcurrently;
    }

    public void init() throws Exception {
        logger.info("开始启动消息消费者服务...");
        // 创建一个消息消费者，并设置一个消息消费者组
        consumer = new DefaultMQPushConsumer(consumerGroupName);
        // 指定NameServer地址
        consumer.setNamesrvAddr(nameServerAddr);
        // 设置consumer第一次启动是从队列头部还是队列尾部开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅指定Topic下的所有消息
        consumer.subscribe(topicName,"*");
        // 注册消息监听器
        consumer.registerMessageListener(messageListenerConcurrently);
        // 消费者对象在使用之前必须调用start方法初始化
        consumer.start();

        logger.info("消息消费者服务启动成功...");
    }

    public void destroy(){
        logger.info("开始关闭消息消费者服务...");
        consumer.shutdown();
        logger.info("消息消费者服务已关闭...");
    }

    public DefaultMQPushConsumer getConsumer(){
        return consumer;
    }
}
