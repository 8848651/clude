package com.orders.xczx_plus_orders_service.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.base.xczx_plus_base.base.exception.XczxPlusException;
import com.base.xczx_plus_base.base.utils.IdWorkerUtils;
import com.base.xczx_plus_base.base.utils.QRCodeUtil;
import com.orders.xczx_plus_orders_model.Dao.XcOrders;
import com.orders.xczx_plus_orders_model.Dao.XcPayRecord;
import com.orders.xczx_plus_orders_model.Dto.AddOrderDto;
import com.orders.xczx_plus_orders_model.Dto.PayRecordDto;
import com.orders.xczx_plus_orders_service.config.AlipayConfig;
import com.orders.xczx_plus_orders_service.mapper.XcOrdersMapper;
import com.orders.xczx_plus_orders_service.mapper.XcPayRecordMapper;
import com.orders.xczx_plus_orders_service.mq.Sending;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl {
    @Value("${alipay.appId}")
    String APP_ID;
    @Value("${alipay.appPrivateKey}")
    String APP_PRIVATE_KEY;
    @Value("${alipay.alipayPublicKey}")
    String ALIPAY_PUBLIC_KEY;
    @Autowired
    XcOrdersMapper XcOrdersMapper;
    @Autowired
    XcPayRecordMapper XcPayRecordMapper;
    @Autowired
    Sending Sending;

    //用来验证是否存在已支付
    public XcPayRecord  ExistsRecord(String id){
        XcPayRecord xcPayRecord = XcPayRecordMapper.selectById(id);
        XcOrders xcOrders = XcOrdersMapper.selectById(xcPayRecord.getOrderId());
        if(xcOrders.getStatus().equals("600002")){
            return null;
        }
        return xcPayRecord;
    }

    //删除没完成记录
    //订单记录id 支付宝id 交易金额
    public void  DeleteRecord(String out_trade_no,String trade_no,String total_amount){
        XcPayRecord xcPayRecord = XcPayRecordMapper.selectById(out_trade_no);
        xcPayRecord.setPaySuccessTime(LocalDateTime.now());
        if(trade_no!=null) {
            xcPayRecord.setOutPayChannel(trade_no);
        }
        xcPayRecord.setStatus("601002");

        LambdaUpdateWrapper<XcOrders> Wrapper = new LambdaUpdateWrapper<>();
        Wrapper.eq(XcOrders::getId,xcPayRecord.getOrderId());
        Wrapper.set(XcOrders::getStatus,"600002");
        XcOrdersMapper.update(null,Wrapper);

        LambdaQueryWrapper<XcPayRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(XcPayRecord::getOrderId,xcPayRecord.getOrderId());
        XcPayRecordMapper.delete(queryWrapper);
        XcPayRecordMapper.insert(xcPayRecord);

        XcOrders xcOrders = XcOrdersMapper.selectById(xcPayRecord.getOrderId());
        System.out.println("xcOrders.getOutBusinessId():"+xcOrders.getOutBusinessId());
        Sending.SendId(xcOrders.getOutBusinessId());
    }

    public XcPayRecord inspect(String PayNoid) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                APP_ID,
                APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                "GBK",
                ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", PayNoid);
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(!response.isSuccess()){
            throw new XczxPlusException("查询结果异常");
        }
        DeleteRecord(PayNoid,null,null);
        return XcPayRecordMapper.selectById(PayNoid);
    }

    //添加订单 和订单记录
    public PayRecordDto Generate( String userId,AddOrderDto addOrderDto) throws IOException {
        System.out.println("addOrderDto:"+addOrderDto);
        XcOrders xcOrders = AddXcOrders(userId, addOrderDto);
        XcPayRecord xcPayRecord=new XcPayRecord();
        long nextId = IdWorkerUtils.getInstance().nextId();
        xcPayRecord.setId(nextId);
        xcPayRecord.setPayNo(nextId);
        xcPayRecord.setOrderId(xcOrders.getId());
        xcPayRecord.setOrderName(addOrderDto.getOrderName());
        xcPayRecord.setTotalPrice(addOrderDto.getTotalPrice());
        xcPayRecord.setCurrency("CNY");
        xcPayRecord.setCreateDate(LocalDateTime.now());
        xcPayRecord.setStatus("601001");
        xcPayRecord.setUserId(userId);
        XcPayRecordMapper.insert(xcPayRecord);
        PayRecordDto PayRecordDto=new PayRecordDto();
        BeanUtils.copyProperties(xcPayRecord,PayRecordDto);
        Long payNo=xcPayRecord.getPayNo();
        QRCodeUtil qrCodeUtil = new QRCodeUtil();
        String url=AlipayConfig.url+"/orders/requestpay?payNo="+payNo;
        System.out.println("url"+url);
        String qrCode = qrCodeUtil.createQRCode(url, 200, 200);
        PayRecordDto.setQrcode(qrCode);
        return PayRecordDto;
    }

    private  XcOrders AddXcOrders(String userId,AddOrderDto addOrderDto){
        LambdaQueryWrapper<XcOrders> wrapper = new LambdaQueryWrapper<XcOrders>();
        wrapper.eq(XcOrders::getOutBusinessId,addOrderDto.getOutBusinessId());
        XcOrders xcOrders = XcOrdersMapper.selectOne(wrapper);
        if(xcOrders==null){
            xcOrders =new XcOrders();
            long nextId = IdWorkerUtils.getInstance().nextId();
            xcOrders.setId(nextId);
            xcOrders.setTotalPrice(addOrderDto.getTotalPrice());
            xcOrders.setCreateDate(LocalDateTime.now());
            //未支付
            xcOrders.setStatus("600001");
            xcOrders.setUserId(userId);
            //订单类型
            xcOrders.setOrderType("60201");
            xcOrders.setOrderName(addOrderDto.getOrderName());
            xcOrders.setOrderDescrip(addOrderDto.getOrderDescrip());
            xcOrders.setOrderDetail(addOrderDto.getOrderDetail());
            xcOrders.setOutBusinessId(addOrderDto.getOutBusinessId());
            XcOrdersMapper.insert(xcOrders);
            return xcOrders;
        }
        return xcOrders;
    }

}
