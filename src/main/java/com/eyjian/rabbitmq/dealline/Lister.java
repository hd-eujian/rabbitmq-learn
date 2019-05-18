package com.eyjian.rabbitmq.dealline;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 死信队里模拟延时队列
 * @Author: yeyongjian
 * @Date: 2019-05-18 14:12
 */
@Component
public class Lister {
    @RabbitListener(queues = DealConstant.REAL_QUEUE)
    public void handle(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        System.out.println(msg);

    }
}
