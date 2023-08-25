package com.messdk.xczx_plus_messdk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.messdk.xczx_plus_messdk.po.MqMessage;

import java.util.List;

public interface MqMessageService {


    List<MqMessage> getMessageList(int shardIndex, int shardTotal, String messageType);

    //任务插入到MqMessage
    MqMessage addMessage(String messageType, String businessKey1, String businessKey2, String businessKey3);

    //阶段处理完成删除MqMessage，插入MqMessageHistory
    int completed(long id);

    //更新阶段处理状态
    int completedStageOne(long id);
    int completedStageTwo(long id);
    int completedStageThree(long id);
    int completedStageFour(long id);

    //获取阶段处理状态
    int getStageOne(long id);
    int getStageTwo(long id);
    int getStageThree(long id);
    int getStageFour(long id);

}
