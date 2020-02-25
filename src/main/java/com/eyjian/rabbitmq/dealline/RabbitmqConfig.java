package com.eyjian.rabbitmq.dealline;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //启动初始化删除绑定用的
//    @PostConstruct
    public void delete() throws IOException {
        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(true);
        channel.queueUnbind(DealConstant.REAL_QUEUE,DealConstant.DEAL_LINE_EXCHANGE,"");
    }
    @Bean
    public Queue initDealLineQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DealConstant.DEAL_LINE_EXCHANGE);
        args.put("x-dead-letter-routing-key", DealConstant.DEAL_LINE_QUEUE);//超时转发的队列
//        args.put("x-message-ttl", 5000);//延时时间
        Queue queue = new Queue(DealConstant.DEAL_LINE_QUEUE,true,false,false,args);
        return queue;
    }
    @Bean
    FanoutExchange dealLineExchange() {
        return new FanoutExchange(DealConstant.DEAL_LINE_EXCHANGE);
    }
    @Bean
    Binding bindingiVewUgcTopicExchange(Queue initRealQueue, FanoutExchange dealLineExchange) {
        return BindingBuilder.bind(initRealQueue).to(dealLineExchange);
    }
    @Bean
    public Queue initRealQueue() {
        return new Queue(DealConstant.REAL_QUEUE);
    }


}
