package com.cn.message.chapter05.demo03;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


/**
 * @author:Alex
 * @date:2019/11/15
 * @version:1.0
 * @description:
 */
public class OrderMessageListener implements MessageListenerOrderly {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
        if(list != null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            logger.info(LocalDateTime.now().format(dateTimeFormatter)+"接收到的消息：");
            // 模拟业务处理消息的时间
            try {
                Thread.sleep(new Random().nextInt(1000));
                for (MessageExt ext:list) {
                    logger.info(LocalDateTime.now().format(dateTimeFormatter)+"消息内容："+new String(ext.getBody(),"UTF-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
