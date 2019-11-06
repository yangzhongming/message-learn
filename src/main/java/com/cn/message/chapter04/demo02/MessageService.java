package com.cn.message.chapter04.demo02;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;


/**
 * @author:Alex
 * @date:2019/11/5
 * @version:1.0
 * @description: Spring整合ActiveMQ
 */
@Service
public class MessageService {
    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
    @Resource(name="testQueue")
    private Destination testQueue;
    @Resource(name="testTopic")
    private Destination testTopic;

    // 向队列发送消息
    public void sendQueueMessage(final String messageContent) {
        jmsTemplate.send(testQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                // 设置消息内容
                msg.setText(messageContent);
                return msg;
            }
        });
    }
    // 向主题发送消息
    public void sendTopicMessage(final String messageContent){
        jmsTemplate.send(testTopic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                // 设置消息内容
                msg.setText(messageContent);
                return msg;
            }
        });
    }
}
