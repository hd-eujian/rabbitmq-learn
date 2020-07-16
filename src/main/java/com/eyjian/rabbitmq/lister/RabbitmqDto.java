package com.eyjian.rabbitmq.lister;

import lombok.Data;

import java.io.Serializable;
@Data
public class RabbitmqDto<T> implements Serializable {

    private T body;

}
