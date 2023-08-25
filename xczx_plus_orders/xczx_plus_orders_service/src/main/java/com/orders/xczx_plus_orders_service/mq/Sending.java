package com.orders.xczx_plus_orders_service.mq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sending {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void SendId(String id) {
        rabbitTemplate.convertAndSend("xczx", "change",id);
    }


}
