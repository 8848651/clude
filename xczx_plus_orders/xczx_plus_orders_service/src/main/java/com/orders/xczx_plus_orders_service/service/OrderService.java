package com.orders.xczx_plus_orders_service.service;

import com.orders.xczx_plus_orders_model.Dao.XcPayRecord;
import com.orders.xczx_plus_orders_model.Dto.AddOrderDto;
import com.orders.xczx_plus_orders_model.Dto.PayRecordDto;

public interface OrderService {

    /**
     * 查询该id有没有被支付过
     * @param id
     * @return
     */
    XcPayRecord ExistsRecord(String id);

    /**
     * 支付完成
     * @param out_trade_no XcOrders 表id
     * @param trade_no 支付宝账单id
     * @param total_amount
     */
    void DeleteRecord(String out_trade_no, String trade_no, String total_amount);

    /**
     * 查询该商品有没有支付成功
     * @param PayNoid
     * @return
     */
    XcPayRecord inspect(String PayNoid);

    /**
     * 添加该商品到支付表
     * @param userId
     * @param addOrderDto
     * @return
     */
    PayRecordDto Generate(String userId, AddOrderDto addOrderDto);

}
