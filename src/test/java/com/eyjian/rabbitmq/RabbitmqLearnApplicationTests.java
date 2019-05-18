package com.eyjian.rabbitmq;

import com.eyjian.rabbitmq.dealline.DealConstant;
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


}
