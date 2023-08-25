package com.orders.xczx_plus_orders_model.Dao;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@ToString
@TableName("xc_orders")
public class XcOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private Long id;

    /**
     * 总价
     */
    private Float totalPrice;

    /**
     * 创建时间
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 用户id
     */
    private String userId;


    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 订单名称
     */
    private String orderName;

    /**
     * 订单描述
     */
    private String orderDescrip;

    /**
     * 订单明细json
     */
    private String orderDetail;


    //外部系统业务id设置了唯一键索引
    private String outBusinessId;


}
