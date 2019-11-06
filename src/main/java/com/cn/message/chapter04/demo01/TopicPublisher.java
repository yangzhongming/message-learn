package com.cn.message.chapter04.demo01;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * @author:Alex
 * @date:2019/11/4
 * @version:1.0
 * @description: Java访问ActiveMQ实例
 */
public class TopicPublisher {
    // 默认用户名
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认密码
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Topic myTestTopic = session.createTopic("activemq-topic-test1");
            MessageProducer producer = session.createProducer(myTestTopic);
            for (int i = 0; i <3 ; i++) {
                TextMessage message = session.createTextMessage("发送消息："+i);
                producer.send(myTestTopic,message);
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
