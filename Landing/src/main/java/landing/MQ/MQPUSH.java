package landing.MQ;

import landing.Dao.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class MQPUSH {

    private final static String exangeName = "Message";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //增
    public void Add(String message) {
        rabbitTemplate.convertAndSend(exangeName, "Add",message);
    }
}
