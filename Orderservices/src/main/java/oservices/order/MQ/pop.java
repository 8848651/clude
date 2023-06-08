package oservices.order.MQ;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class pop {
    private final static String exangeName = "Hotel";


    //增
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "EsOrderAdd"),
            key={"Add"}
    ))
    public void Add(String msg) throws InterruptedException {
        System.out.println( "ADD:"+msg);
        Thread.sleep(200);
    }

    //删
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "EsOrderDelete"),
            key={"Delete"}
    ))
    public void Delete(String msg) throws InterruptedException {
        System.out.println( "Delete:"+msg);
        Thread.sleep(200);
    }

    //改
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "EsOrderChange"),
            key={"Change"}
    ))
    public void Change(String msg) throws InterruptedException {
        System.out.println( "Change:"+msg);
        Thread.sleep(200);
    }
}
