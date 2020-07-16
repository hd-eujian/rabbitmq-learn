package com.eyjian.rabbitmq.lister;

public abstract class GacRabbitmqLister <T>  {

    public abstract String getQueueName();

    public abstract void mqHandler(RabbitmqDto<T> rabbitmqDto);
}
