package com.eyjian.rabbitmq;

import com.eyjian.rabbitmq.dealline.DealConstant;
import com.rabbitmq.client.Channel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqLearnApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads() throws IOException {
        rabbitTemplate.convertAndSend(DealConstant.DEAL_LINE_QUEUE,"hell word");
    }

    @Test
    public void deletequeue() throws IOException {
        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(true);
//        channel.queueDelete(DealConstant.DEAL_LINE_QUEUE);

        channel.queueUnbind(DealConstant.REAL_QUEUE,DealConstant.DEAL_LINE_EXCHANGE,"");
    }

}
