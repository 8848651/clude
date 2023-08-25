package com.messdk.xczx_plus_messdk.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("mq_message_history")
public class MqMessageHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String messageType;
    private String businessKey1;
    private String businessKey2;
    private String businessKey3;
    private Integer executeNum;
    private Integer state;
    private LocalDateTime returnfailureDate;
    private LocalDateTime returnsuccessDate;
    private String returnfailureMsg;
    private LocalDateTime executeDate;
    private String stageState1;
    private String stageState2;
    private String stageState3;
    private String stageState4;

}
