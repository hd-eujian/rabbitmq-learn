package com.eyjian.rabbitmq.controller;

import com.eyjian.rabbitmq.lister.GacRabbitmqLister;
import com.eyjian.rabbitmq.lister.RabbitmqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class Aware implements ApplicationRunner {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private List<GacRabbitmqLister> rabbitmqListerList;


    private Map<String,Integer> errMap = new HashMap<>();
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection connection = rabbitTemplate.getConnectionFactory().createConnection();


        rabbitmqListerList.forEach(r->{

            try {

                String queueName = r.getQueueName();
                Channel channel = connection.createChannel(false);
                channel.queueDeclare(queueName,true,false,false,new HashMap<>());
                DeliverCallback deliverCallback = new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery delivery) throws IOException {
                        long deliveryTag = delivery.getEnvelope().getDeliveryTag();
                        String messageId = delivery.getProperties().getMessageId();
                        try {
                            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                            ObjectMapper mapper = new ObjectMapper();
                            RabbitmqDto rabbitmqDto = mapper.readValue(message, RabbitmqDto.class);
                            r.mqHandler(rabbitmqDto);
                            channel.basicAck(deliveryTag, false); //手动确认消息
                        }catch (Exception e){
                            log.error("我消费失败",e);
                            boolean containsKey = errMap.containsKey(messageId);
                            if(containsKey){
                                channel.basicReject(deliveryTag, false); //手动确认消息
                            }else {
                                channel.basicReject(deliveryTag, true); //手动确认消息
                                errMap.put(messageId,1);
                            }

                        }



                    }
                };
                CancelCallback cancelCallback = null;
                channel.basicConsume(queueName, false, deliverCallback, cancelCallback);
            }catch (Exception e){
                log.error("",e);
            }


        });


    }
}
