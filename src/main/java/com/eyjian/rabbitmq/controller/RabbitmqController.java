package com.eyjian.rabbitmq.controller;

import com.eyjian.rabbitmq.dealline.DealConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping
@RestController
public class RabbitmqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/demo")
    public String demo(Long ttl){
        MessageProperties messageProperties = new MessageProperties();
        Long delayTime = ttl;
        messageProperties.setExpiration(delayTime.toString());
        String messageBody = "发送的延迟时间:"+ttl;
        Message message = rabbitTemplate.getMessageConverter().toMessage(messageBody, messageProperties);

        rabbitTemplate.convertAndSend(DealConstant.DEAL_LINE_QUEUE,message);
        return "success";
    }

}
