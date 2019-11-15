package com.cn.message.chapter05.demo03;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;


/**
 * @author:Alex
 * @date:2019/11/14
 * @version:1.0
 * @description:
 */
public class OrderProducerTest {
    private ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-rocketmq-producer03.xml");
    }

    @Test
    public void sendMessage() throws Exception {
        OrderProducer orderProducer = applicationContext.getBean(OrderProducer.class);
        OrderMessageQueueSelector messageQueueSelector = applicationContext.getBean(OrderMessageQueueSelector.class);
        String topicName= "topic_example_order";
        String[] statusName={"已创建","已付款","已配送","已取消","已完成"};
        // 模拟订单消息
        for (int orderId = 1; orderId <=10 ; orderId ++) {
            for (int i = 0; i < statusName.length; i++) {
                String messageContent = new OrderMessage().
                        setId(orderId).setStatus(statusName[i]).
                        setSendOrder(i).
                        setContent("Hello world...").
                        toString();
                Message sendMessage = new Message(topicName,statusName[i],
                        orderId+"#"+statusName[i],messageContent.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = orderProducer.getProducer().send(sendMessage,messageQueueSelector,orderId);
                System.out.println(sendResult);
            }
        }

    }
}
