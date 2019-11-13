package com.cn.message.chapter05.demo02;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;


/**
 * @author:Alex
 * @date:2019/11/13
 * @version:1.0
 * @description:
 */
public class MessageListener implements MessageListenerConcurrently {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
       if (list!=null){
           for (MessageExt ext:list){
               logger.info("监听到消息："+ new String(ext.getBody()));
           }
       }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
