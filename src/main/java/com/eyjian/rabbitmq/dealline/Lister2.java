package com.eyjian.rabbitmq.dealline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class Lister2 {
//    @RabbitListener(queues = "nodesqueue")
    @RabbitListener(queues = DealConstant.REAL_QUEUE)
    public void handle(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        log.info("nodesqueue={}",msg);

    }
}
