package com.eyjian.rabbitmq.dealline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 死信队里模拟延时队列
 * @Author: yeyongjian
 * @Date: 2019-05-18 14:12
 */
@Slf4j
@Component
public class Lister {
    @RabbitListener(queues = DealConstant.REAL_QUEUE)
    public void handle(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("接受到的延时msg={}",msg);

    }
}
