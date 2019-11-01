package com.cn.message.learn01;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author:Alex
 * @date:2019/10/29
 * @version:1.0
 * @description: 消息处理中心类
 */
public class Broker {
    // 队列存储消息的最大数量
    private final static int MAX_SIZE = 3;

    // 保存消息数据的容器
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    // 生产消息
    public static void prdouce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("成功向消息中心投递数据：" + msg + "当前暂存的消息数量是："+messageQueue.size());
        } else {
            System.out.println("消息处理中心内存暂存的消息达到最大负荷，不能继续放入消息");
        }
    }

    public static String consume(){
        String msg = messageQueue.poll();
        if (msg!=null){
            System.out.println("已经消费消息："+ msg+ "当前暂存的消息数量是："+messageQueue.size());
        } else {
            System.out.println("消息中心没有可以消费的是消息");
        }
        System.out.println("=====================================");
        return msg;
    }

}
