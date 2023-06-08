package amqp.pop;


import amqp.push.student;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class pop {

    //直接传入队列
    @RabbitListener(queues = "simple.queue")
    public void get(String msg) throws InterruptedException {
        System.out.println("simple.queue:"+msg);
        Thread.sleep(200);
    }

    //配置config
    @RabbitListener(queues = "fanoutQueue1")
    public void getQueue1(String msg) throws InterruptedException {
        System.out.println( "fanoutQueue1:"+msg);
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanoutQueue2")
    public void getQueue2(String msg) throws InterruptedException {
        System.out.println( "fanoutQueue2:"+msg);
        Thread.sleep(200);
    }

    //不配置config direct 接收普通字符串
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = "Fanout2-exange",type = "direct"),
            value = @Queue(name = "ExchangeQueu1"),
            key={"red","blue"}
    ))
    public void getexange1(String msg) throws InterruptedException {
        System.out.println( "Fanout2-exange:ExchangeQueu1:"+msg);
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = "Fanout2-exange",type = "direct"),
            value = @Queue(name = "ExchangeQueu2"),
            key={"red","yellow"}
    ))
    public void getexange2(String msg) throws InterruptedException {
        System.out.println( "Fanout2-exang:ExchangeQueu2:"+msg);
        Thread.sleep(200);
    }

    //不配置config direct 接收json或对象
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = "Fanout3-exange",type = "direct"),
            value = @Queue(name = "JsonQueu1"),
            key={"red","blue"}
    ))
    public void getjson1(student student) throws InterruptedException {
        System.out.println( "Fanout3-exange:JsonQueu1:"+student.toString());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = "Fanout3-exange",type = "direct"),
            value = @Queue(name = "JsonQueu2"),
            key={"red","yellow"}
    ))
    public void getjson2(Map<String,Object> map) throws InterruptedException {
        System.out.println( "Fanout3-exange:JsonQueu2:"+map);
        Thread.sleep(200);
    }
}
