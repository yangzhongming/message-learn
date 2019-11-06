package com.cn.message.chapter04.demo01;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author:Alex
 * @date:2019/11/5
 * @version:1.0
 * @description:
 */
public class TopicSubscriber {
    // 默认用户名
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认密码
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
            try {
                Connection connection = connectionFactory.createConnection();
                connection.start();
                Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
                Topic myTestTopic = session.createTopic("activemq-topic-test1");
                MessageConsumer messageConsumer = session.createConsumer(myTestTopic);
                messageConsumer.setMessageListener(new MessageListener() {
                    public void onMessage(Message message) {
                        try {
                            System.out.println("消费者1接收到消息：" + ((TextMessage)message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
                MessageConsumer messageConsumer2 = session.createConsumer(myTestTopic);
                messageConsumer2.setMessageListener(new MessageListener() {
                    public void onMessage(Message message) {
                        try {
                            System.out.println("消费者2接收到消息：" + ((TextMessage)message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
                MessageConsumer messageConsumer3 = session.createConsumer(myTestTopic);
                messageConsumer3.setMessageListener(new MessageListener() {
                    public void onMessage(Message message) {
                        try {
                            System.out.println("消费者3接收到消息：" + ((TextMessage)message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(100*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
