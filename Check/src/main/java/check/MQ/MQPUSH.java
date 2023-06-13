package check.MQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MQPUSH {

    private final static String exangeName = "Message";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //删
    public void Delete(String id) {
        rabbitTemplate.convertAndSend(exangeName, "Delete",id);
    }

    //改
    public void Change(String message) {
        rabbitTemplate.convertAndSend(exangeName, "Change",message);
    }

}
