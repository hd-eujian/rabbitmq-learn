package com.eyjian.rabbitmq.dealline;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue initRealQueue() {
        return new Queue(DealConstant.REAL_QUEUE);
    }


}
