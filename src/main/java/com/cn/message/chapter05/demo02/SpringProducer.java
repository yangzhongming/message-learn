package com.cn.message.chapter05.demo02;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @author:Alex
 * @date:2019/11/13
 * @version:1.0
 * @description:
 */
public class SpringProducer {
    private Logger logger = Logger.getLogger(getClass());
    private String producerGroupName;
    private String nameServerAddr;
    private DefaultMQProducer producer;

    public SpringProducer(String producerGroupName,String nameServerAddr){
        this.producerGroupName = producerGroupName;
        this.nameServerAddr = nameServerAddr;
    }

    public void init() throws Exception {
        logger.info("开始启动消息生产者服务...");
        // 创建一个消息生产者，并设置一个消息生产者组
        producer = new DefaultMQProducer(producerGroupName);
        // 指定NameServer地址
        producer.setNamesrvAddr(nameServerAddr);
        // 初始化Springproducer,在整个应用生命周期内只需要初始化一次
        producer.start();
        logger.info("消息生产者服务启动成功...");
    }

    public void destroy(){
        logger.info("开始关闭消息生产者服务...");
        producer.shutdown();
        logger.info("消息生产者服务已关闭...");
    }

    public DefaultMQProducer getProducer(){
        return producer;
    }

}
