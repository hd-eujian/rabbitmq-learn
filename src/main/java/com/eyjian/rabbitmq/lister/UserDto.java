package com.eyjian.rabbitmq.lister;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Integer userId;

}
