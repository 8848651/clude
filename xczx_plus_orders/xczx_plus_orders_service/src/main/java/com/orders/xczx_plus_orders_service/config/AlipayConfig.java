package com.orders.xczx_plus_orders_service.config;


 public class AlipayConfig {
  public static String url="http://euufmz.natappfree.cc";
  // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
  public static String notify_url = url+"/orders/paynotify";
  // 请求网关地址
  public static String URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
  // 编码
  public static String CHARSET = "UTF-8";
  // 返回格式
  public static String FORMAT = "json";
  // 日志记录目录
  public static String log_path = "/log";
  // RSA2
  public static String SIGNTYPE = "RSA2";

 }
