package com.cn.message.chapter04.demo02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:Alex
 * @date:2019/11/5
 * @version:1.0
 * @description:
 */
public class StartApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jms.xml");
        MessageService messageService = (MessageService) ctx.getBean("messageService");
        messageService.sendQueueMessage("我的测试消息1...");
        messageService.sendTopicMessage("我的测试消息2...");
        messageService.sendTopicMessage("我的测试消息3...");
    }
}
