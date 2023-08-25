package com.messdk.xczx_plus_messdk.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@TableName("mq_message")
public class MqMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String messageType;
    private String businessKey1;
    private String businessKey2;
    private String businessKey3;
    private Integer executeNum;
    //处理状态，0:初始，1:成功',
    private String state;
    private LocalDateTime returnfailureDate;
    private LocalDateTime returnsuccessDate;
    private String returnfailureMsg;
    private LocalDateTime executeDate;
    //阶段1处理状态, 0:初始，1:成功
    private String stageState1;
    //阶段2处理状态, 0:初始，1:成功
    private String stageState2;
    //阶段3处理状态, 0:初始，1:成功
    private String stageState3;
    //阶段4处理状态, 0:初始，1:成功
    private String stageState4;


}
