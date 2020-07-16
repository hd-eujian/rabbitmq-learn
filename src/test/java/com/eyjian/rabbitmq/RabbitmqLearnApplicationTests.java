package com.eyjian.rabbitmq;

import com.eyjian.rabbitmq.lister.RabbitmqDto;
import com.eyjian.rabbitmq.lister.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqLearnApplicationTests {
    //policy
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads() throws IOException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.convertAndSend(DealConstant.REAL_QUEUE,"hell word");
//        rabbitTemplate.convertAndSend("demo","hello1");
        RabbitmqDto<UserDto> rabbitmqDto = new RabbitmqDto<>();
        UserDto userDto = new UserDto();
        userDto.setUserId(23);
        rabbitmqDto.setBody(userDto);
        rabbitTemplate.convertAndSend("MyLister",rabbitmqDto);
//        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
//        AMQP.Exchange.DeclareOk exc = channel.exchangeDeclare("exc", BuiltinExchangeType.FANOUT);
//        Map<String, Object> map = new HashMap<>();
//        map.put( "passive",true);
//        AMQP.Queue.DeclareOk a = channel.queueDeclare("a", false, false, false, map);
//        String queue = a.getQueue();
//        System.out.println(queue);
    }


}
