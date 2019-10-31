package com.cn.message.learn04;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;

/**
 * @author:Alex
 * @date:2019/10/31
 * @version:1.0
 * @description:
 */
public class MailMessageListener implements MessageListener {
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Mail mail = mapper.readValue(messageBody,Mail.class);
            System.out.println("接收到邮件消息："+mail);
            sendMail();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("接收到消息："+messageBody);
    }

    private void sendMail() {
        System.out.println("调用发送邮件");
    }
}
