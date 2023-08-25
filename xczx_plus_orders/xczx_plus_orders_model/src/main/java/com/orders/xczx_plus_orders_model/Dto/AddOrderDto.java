package com.orders.xczx_plus_orders_model.Dto;

import lombok.Data;
import lombok.ToString;
//创建商品订单
@Data
@ToString
public class AddOrderDto  {

    //总价
    private Float totalPrice;
    //订单类型
    private String orderType;
    //订单名称
    private String orderName;
    //订单描述
    private String orderDescrip;
    /**
     * 订单明细json，不可为空
     * [{"goodsId":"","goodsType":"","goodsName":"","goodsPrice":"","goodsDetail":""},{...}]
     */
    private String orderDetail;
   //XcOrders 主键
    private String outBusinessId;

}
