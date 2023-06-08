package amqp.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class push {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private student student;

    //发送给队列
    public void SetQ() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    //发送给交换机config
    public void SetE() {
        String exangeName = "Fanout-exange";
        String message = "hello, Fanout-exange";
        rabbitTemplate.convertAndSend(exangeName, "",message);
    }

    //发送给交换机没有config
    public void SetE01() {
        String exangeName = "Fanout2-exange";
        String message = "hello, Fanout2-exange:red";
        rabbitTemplate.convertAndSend(exangeName, "red",message);
    }
    public void SetE02() {
        String exangeName = "Fanout2-exange";
        String message = "hello, Fanout2-exange:yellow";
        rabbitTemplate.convertAndSend(exangeName, "yellow",message);
    }

    //将对象转为json发送
    public void SetjsonString1() throws JsonProcessingException {
        String exangeName = "Fanout2-exange";
        student.setName("张三");
        student.setAge(18);
        ObjectMapper oo=new ObjectMapper();
        String json = oo.writeValueAsString(student);
        rabbitTemplate.convertAndSend(exangeName, "yellow",json);
    }

    public void SetjsonString2() throws JsonProcessingException {
        String exangeName = "Fanout2-exange";
        Map<String,Object> map=new HashMap<>();
        map.put("张三",18);
        map.put("李四",19);
        ObjectMapper oo=new ObjectMapper();
        String json = oo.writeValueAsString(map);
        rabbitTemplate.convertAndSend(exangeName, "blue",json);
    }

    //将对象转发送
    public void Setjson1() throws JsonProcessingException {
        String exangeName = "Fanout2-exange";
        student.setName("张三");
        student.setAge(18);
        rabbitTemplate.convertAndSend(exangeName, "yellow",student);
    }

    public void Setjson2() {
        String exangeName = "Fanout3-exange";
        Map<String,Object> map=new HashMap<>();
        map.put("张三",18);
        map.put("李四",19);
        rabbitTemplate.convertAndSend(exangeName, "blue",map);
    }
}
