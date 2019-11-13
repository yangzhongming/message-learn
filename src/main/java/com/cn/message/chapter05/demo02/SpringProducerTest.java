package com.cn.message.chapter05.demo02;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:Alex
 * @date:2019/11/13
 * @version:1.0
 * @description:
 */
public class SpringProducerTest {
    private ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-rocketmq-producer.xml");
    }

    @Test
    public  void  sendMessage() throws Exception {
        SpringProducer producer = applicationContext.getBean(SpringProducer.class);

        for (int i = 0; i <20 ; i++) {
            Message message = new Message("spring-rocketMQ-topic",null,("Spring RocketMQ demo" + i).getBytes(
                    RemotingHelper.DEFAULT_CHARSET
            ));
            // 发送消息并返回结果
            SendResult sendResult = producer.getProducer().send(message);
            System.out.println(sendResult);
        }



    }
}
