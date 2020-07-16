package com.eyjian.rabbitmq.lister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MyLister extends GacRabbitmqLister  {
    @Override
    public String getQueueName() {
        return "MyLister";
    }

    @Override
    public void mqHandler(RabbitmqDto rabbitmqDto) {
        System.out.println("MyLister:"+rabbitmqDto);
    }
}
