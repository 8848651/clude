package com.orders.xczx_plus_orders_model.Dto;

import lombok.Data;
import lombok.ToString;

//支付结果数据, 用于接收支付结果通知处理逻辑
@Data
@ToString
public class PayStatusDto {

    //商户订单号
    String out_trade_no;
    //支付宝交易号
    String trade_no;
    //交易状态
    String trade_status;
    //appid
    String app_id;
    //total_amount
    String total_amount;
}
