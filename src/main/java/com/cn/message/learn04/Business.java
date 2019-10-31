package com.cn.message.learn04;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:Alex
 * @date:2019/10/31
 * @version:1.0
 * @description:RabbitMq的异步处理
 */
public class Business {

    public void userRegister(){
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        Mail mail = new Mail();
        mail.setTo("test@163.com");
        mail.setFrom("test@qq.com");
        mail.setSubject("邮件标题");
        mail.setContent("邮件内容");
        template.convertAndSend(mail.toString());
        ctx.close();
    }
    public static void main(String[] args) {
        Business business = new Business();
        business.userRegister();
    }


}
