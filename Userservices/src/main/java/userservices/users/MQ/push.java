package userservices.users.MQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class push {
    private final static String exangeName = "Hotel";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //增
    public void Add(String message) {
        rabbitTemplate.convertAndSend(exangeName, "Add",message);
    }

    //删
    public void Delete(String message) {
        rabbitTemplate.convertAndSend(exangeName, "Delete",message);
    }

    //改
    public void Change(String message) {
        rabbitTemplate.convertAndSend(exangeName, "Change",message);
    }

}
