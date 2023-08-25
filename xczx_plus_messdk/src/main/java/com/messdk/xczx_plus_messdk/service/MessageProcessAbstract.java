package com.messdk.xczx_plus_messdk.service;

import com.messdk.xczx_plus_messdk.po.MqMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.*;

@Data
public abstract class MessageProcessAbstract {
    private static ThreadPoolExecutor pool;
    @Autowired
    private MqMessageService mqMessageService;

    static {
        pool = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
    public abstract boolean execute(MqMessage mqMessage);

    public void process(int shardIndex, int shardTotal,  String messageType,long timeout) {
        try {
            List<MqMessage> messageList = mqMessageService.getMessageList(shardIndex, shardTotal,messageType);
            int size = messageList.size();
            if(size<=0){
                return ;
            }
            CountDownLatch countDownLatch = new CountDownLatch(size);
            messageList.forEach(message -> {
                pool.execute(() -> {
                    try {
                        boolean execute = execute(message);
                        if(execute){
                            mqMessageService.completed(message.getId());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                       }finally {
                        countDownLatch.countDown();
                    }
                });
            });
            countDownLatch.await(timeout,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}
