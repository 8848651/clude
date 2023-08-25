package com.orders.xczx_plus_orders_model.Dto;
import com.orders.xczx_plus_orders_model.Dao.XcPayRecord;
import lombok.Data;
import lombok.ToString;

//支付记录dto
@Data
@ToString
public class PayRecordDto extends XcPayRecord {

    //二维码
    private String qrcode;

}
