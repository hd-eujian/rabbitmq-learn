package com.eyjian.rabbitmq.lister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyLister2 extends GacRabbitmqLister<Integer>  {
    @Override
    public String getQueueName() {
        return "MyLister2";
    }

    @Override
    public void mqHandler(RabbitmqDto<Integer> rabbitmqDto) {
        Integer body = rabbitmqDto.getBody();
        System.out.println("MyLister2:"+body);
    }




}
