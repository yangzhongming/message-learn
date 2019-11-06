package com.cn.message.chapter05.demo01;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author:Alex
 * @date:2019/11/6
 * @version:1.0
 * @description: Java访问RocketMQ
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 创建一个消息生产者，并设置一个消息生产者组
        DefaultMQProducer producer = new DefaultMQProducer("my_producer_group");
        // 指定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        // 初始化Poducer,在整个应用生命周期中只需要初始化一次
        producer.start();
        for (int i = 0; i <100 ; i++) {      // 创建一个消息对象，指定其主题、标签和消息内容
            Message msg = new Message("topic_example_java",
                    "TagA",
                    ("Hello Java demo RocketMQ"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息并返回结果
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n",sendResult);

        }
        // 清理资源、关闭网络连接
        producer.shutdown();
    }
}
