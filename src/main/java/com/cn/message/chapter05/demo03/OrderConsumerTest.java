package com.cn.message.chapter05.demo03;

import com.cn.message.chapter05.demo02.SpringConsumer;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.requests.OrderingRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:Alex
 * @date:2019/11/13
 * @version:1.0
 * @description:
 */
public class OrderConsumerTest {
    private ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-rocketmq-consumer03.xml");
    }

    @Test
    public void consume() throws Exception {
        OrderConsumer consumer = applicationContext.getBean(OrderConsumer.class);
        Thread.sleep(200*1000);
        consumer.destroy();
    }
}
