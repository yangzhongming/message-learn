package com.cn.message.chapter05.demo03;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author:Alex
 * @date:2019/11/14
 * @version:1.0
 * @description:
 */
public class OrderMessageQueueSelector implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> list, Message message, Object orderId) {
        Integer id  = (Integer) orderId;

        return list.get(id%list.size());
    }
}
