package com.orders.xczx_plus_orders_api;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.orders.xczx_plus_orders_model.Dto.AddOrderDto;
import com.orders.xczx_plus_orders_model.Dto.PayRecordDto;
import com.orders.xczx_plus_orders_service.config.AlipayConfig;
import com.orders.xczx_plus_orders_service.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class XczxPlusOrdersApiApplicationTests {
    @Value("${alipay.appId}")
    String APP_ID;
    @Value("${alipay.appPrivateKey}")
    String APP_PRIVATE_KEY;
    @Value("${alipay.alipayPublicKey}")
    String ALIPAY_PUBLIC_KEY;

    @Autowired
    OrderServiceImpl OrderServiceImpl;
    @Test
    void test() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi-sandbox.dl.alipaydev.com/gateway.do",
                APP_ID,
                APP_PRIVATE_KEY,
                "json",
                "GBK",
                ALIPAY_PUBLIC_KEY,
                "RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "20230320010102052");
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

    }

    @Test
    void test2() throws IOException {
        AddOrderDto addOrderDto=new AddOrderDto();
        addOrderDto.setOrderDescrip("aaa");
        addOrderDto.setOrderDetail("bbb");
        addOrderDto.setOrderName("123");
        addOrderDto.setOrderType("000");
        addOrderDto.setOutBusinessId("123");
        addOrderDto.setTotalPrice((float) 10);
        PayRecordDto generate = OrderServiceImpl.Generate("10", addOrderDto);
        System.out.println(generate );
    }
}
