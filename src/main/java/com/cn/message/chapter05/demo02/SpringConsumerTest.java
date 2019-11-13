package com.cn.message.chapter05.demo02;

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
public class SpringConsumerTest {
    private ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-rocketmq-consumer.xml");
    }

    @Test
    public void consume() throws Exception {
        SpringConsumer consumer = applicationContext.getBean(SpringConsumer.class);
        Thread.sleep(200*1000);
        consumer.destroy();
    }
}
