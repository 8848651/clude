package com.messdk.xczx_plus_messdk.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.messdk.xczx_plus_messdk.mapper.MqMessageHistoryMapper;
import com.messdk.xczx_plus_messdk.mapper.MqMessageMapper;
import com.messdk.xczx_plus_messdk.po.MqMessage;
import com.messdk.xczx_plus_messdk.po.MqMessageHistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqMessageServiceImpl implements MqMessageService {

    @Autowired
    MqMessageMapper mqMessageMapper;

    @Autowired
    MqMessageHistoryMapper mqMessageHistoryMapper;


    @Override
    public List<MqMessage> getMessageList(int shardIndex, int shardTotal, String messageType) {
        List<MqMessage> mqMessages = mqMessageMapper.selectListByShardIndex(shardTotal, shardIndex, messageType);
        return mqMessages;
    }

    @Override
    public MqMessage addMessage(String messageType, String businessKey1, String businessKey2, String businessKey3) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setMessageType(messageType);
        mqMessage.setBusinessKey1(businessKey1);
        mqMessage.setBusinessKey2(businessKey2);
        mqMessage.setBusinessKey3(businessKey3);
        int insert = mqMessageMapper.insert(mqMessage);
        if(insert>0){
            return mqMessage;
        }else{
            return null;
        }

    }

    @Override
    public int completed(long id) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setState("1");
        LambdaQueryWrapper<MqMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MqMessage::getId, id);
        int update = mqMessageMapper.update(mqMessage,wrapper);
        if(update>0){
            mqMessage = mqMessageMapper.selectById(id);
            MqMessageHistory mqMessageHistory = new MqMessageHistory();
            BeanUtils.copyProperties(mqMessage,mqMessageHistory);
            mqMessageHistoryMapper.insert(mqMessageHistory);
            mqMessageMapper.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int completedStageOne(long id) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setStageState1("1");
        LambdaQueryWrapper<MqMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MqMessage::getId,id);
        return mqMessageMapper.update(mqMessage,wrapper);
    }

    @Override
    public int completedStageTwo(long id) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setStageState2("1");
        LambdaQueryWrapper<MqMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MqMessage::getId,id);
        return mqMessageMapper.update(mqMessage,wrapper);
    }

    @Override
    public int completedStageThree(long id) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setStageState3("1");
        LambdaQueryWrapper<MqMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MqMessage::getId,id);
        return mqMessageMapper.update(mqMessage,wrapper);
    }

    @Override
    public int completedStageFour(long id) {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setStageState4("1");
        LambdaQueryWrapper<MqMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MqMessage::getId,id);
        return mqMessageMapper.update(mqMessage,wrapper);
    }

    @Override
    public int getStageOne(long id) {
        MqMessage mqMessage = mqMessageMapper.selectById(id);
        String stageState1 = mqMessage.getStageState1();
        return Integer.parseInt(stageState1);
    }

    @Override
    public int getStageTwo(long id) {
        MqMessage mqMessage = mqMessageMapper.selectById(id);
        String stageState2 = mqMessage.getStageState2();
        return Integer.parseInt(stageState2);
    }

    @Override
    public int getStageThree(long id) {
        MqMessage mqMessage = mqMessageMapper.selectById(id);
        String stageState3 = mqMessage.getStageState3();
        return Integer.parseInt(stageState3);
    }

    @Override
    public int getStageFour(long id) {
        MqMessage mqMessage = mqMessageMapper.selectById(id);
        String stageState4 = mqMessage.getStageState4();
        return Integer.parseInt(stageState4);
    }


}
