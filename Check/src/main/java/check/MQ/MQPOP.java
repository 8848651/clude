package check.MQ;
import check.Dao.Message;
import check.ES.Estart;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class MQPOP {
    private final static String exangeName = "Message";

    @Autowired
    private Estart es;

    //增
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "Add"),
            key={"Add"}
    ))
    public void Add(String message) throws InterruptedException, IOException {
        Message message1 = JSON.parseObject(message, Message.class);
        System.out.println("MQPOP"+message1.toString());
        es.testAdd(JSON.toJSONString(message1),message1.getId());
    }
}
