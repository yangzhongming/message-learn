package com.cn.message.chapter05.demo03;

/**
 * @author:Alex
 * @date:2019/11/14
 * @version:1.0
 * @description:
 */
public class OrderMessage {
    // 订单Id
    private int id;
    // 订单状态
    private String status;
    // 订单消息发送顺序
    private int sendOrder;
    // 订单内容
    private String content;

    public int getId() {
        return id;
    }

    public OrderMessage setId(int id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderMessage setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getSendOrder() {
        return sendOrder;
    }

    public OrderMessage setSendOrder(int sendOrder) {
        this.sendOrder = sendOrder;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OrderMessage setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString(){
        return "订单消息:{"+
                "订单Id="+ id +
                ",订单状态="+status +
                ",订单消息发送顺序=" + sendOrder +
                ",订单内容=" + content +
                "}";
    }
}
