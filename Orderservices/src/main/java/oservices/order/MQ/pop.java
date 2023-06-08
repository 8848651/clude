package oservices.order.MQ;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oservices.order.Dao.HotelDoc;
import oservices.order.ES.Estart;

import java.io.IOException;

@Component
public class pop {
    private final static String exangeName = "Hotel";

    @Autowired
    private Estart es;

    //增
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "EsOrderAdd"),
            key={"Add"}
    ))
    public void Add(String msg) throws InterruptedException, IOException {
        HotelDoc hotelDoc = JSON.parseObject(msg, HotelDoc.class);
        es.testAddDocument(msg,hotelDoc.getId().toString());
    }

    //删
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "EsOrderDelete"),
            key={"Delete"}
    ))
    public void Delete(String id) throws InterruptedException, IOException {
        es.testDeleteDocumentById(id);
    }

    //改
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(name = exangeName,type = "direct"),
            value = @Queue(name = "EsOrderChange"),
            key={"Change"}
    ))
    public void Change(String msg) throws InterruptedException, IOException {
        HotelDoc hotelDoc = JSON.parseObject(msg, HotelDoc.class);
        es.testExchangeDocumentById(msg,hotelDoc.getId().toString());
    }
}
