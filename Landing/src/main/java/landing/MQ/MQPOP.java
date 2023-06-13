package landing.MQ;
import com.alibaba.fastjson.JSON;
import landing.Dao.Message;
import landing.Service.MesssageService.MessageImp;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MQPOP {

    @Autowired
    private MessageImp MI;

    private final static String exangeName = "Message";

    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "Delete"),
            key={"Delete"}
    ))
    public void Delete(String id) throws InterruptedException, IOException {
        MI.Delete(id);
    }

    //改
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "Change"),
            key={"Change"}
    ))
    public void Change(String msg) throws InterruptedException, IOException {
        Message message = JSON.parseObject(msg, Message.class);
        MI.Change(message);
    }
}
