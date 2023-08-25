package com.orders.xczx_plus_orders_api.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.base.xczx_plus_base.base.exception.XczxPlusException;
import com.orders.xczx_plus_orders_api.config.SecurityUtil;
import com.orders.xczx_plus_orders_model.Dao.XcPayRecord;
import com.orders.xczx_plus_orders_model.Dto.AddOrderDto;
import com.orders.xczx_plus_orders_model.Dto.PayRecordDto;
import com.orders.xczx_plus_orders_service.config.AlipayConfig;
import com.orders.xczx_plus_orders_service.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
@Controller
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderServiceImpl OrderServiceImpl;

    @Value("${alipay.appId}")
    String APP_ID;
    @Value("${alipay.appPrivateKey}")
    String APP_PRIVATE_KEY;
    @Value("${alipay.alipayPublicKey}")
    String ALIPAY_PUBLIC_KEY;


    //生成支付二维码
    @PostMapping("/generatepaycode")
    @ResponseBody
    public PayRecordDto generatePayCode(@RequestBody AddOrderDto addOrderDto) throws IOException {
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        String userId = user.getId();
        PayRecordDto generate = OrderServiceImpl.Generate(userId, addOrderDto);
        return generate;
    }

    //查询支付结果
    @GetMapping("/payresult")
    @ResponseBody
    public XcPayRecord payresult(String payNo) throws AlipayApiException {
        System.out.println("查询支付结果单号："+payNo);
        XcPayRecord inspect = OrderServiceImpl.inspect(payNo);
        return inspect;

    }

    //扫码下单接口
    @GetMapping("/requestpay")
    public void requestpay(String payNo, HttpServletResponse httpResponse) throws IOException, AlipayApiException {
        //传入了支付记录号，判断支付记录号是否存在
        System.out.println("payNo:"+payNo);
        XcPayRecord payRecordByPayno =  OrderServiceImpl.ExistsRecord(payNo);
        if (payRecordByPayno==null) {
            XczxPlusException.cast("已支付，无需重复支付");
        }

        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                APP_ID,
                APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+payNo+"\"," +
                "    \"total_amount\":"+payRecordByPayno.getTotalPrice()+"," +
                "    \"subject\":\""+payRecordByPayno.getOrderName()+"\"," +
                "    \"product_code\":\"QUICK_WAP_WAY\"" +
                "  }");
        String form = alipayClient.pageExecute(alipayRequest).getBody();
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
    }

    //接收支付宝通知
    @PostMapping("/paynotify")
    public void paynotify(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean verify_result = AlipaySignature.rsaCheckV1(
                params,
                ALIPAY_PUBLIC_KEY,
                AlipayConfig.CHARSET,
                AlipayConfig.SIGNTYPE);
        if(verify_result){
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //交易金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            if (trade_status.equals("TRADE_SUCCESS")){
                System.out.println("out_trade_no"+out_trade_no);
                OrderServiceImpl.DeleteRecord(out_trade_no,trade_no,total_amount);
            }
            response.getWriter().write("success");
        }else{
            response.getWriter().write("fail");
        }
    }
}
