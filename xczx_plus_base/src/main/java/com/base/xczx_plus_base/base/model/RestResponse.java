package com.base.xczx_plus_base.base.model;

import lombok.Data;
import lombok.ToString;


 @Data
 @ToString
public class RestResponse<T> {

  private int code;

  private String msg;

  private T result;


  public RestResponse() {
   this(0, "success");
  }

  public RestResponse(int code, String msg) {
   this.code = code;
   this.msg = msg;
  }

  public static <T> RestResponse<T> validfail(String msg) {
   RestResponse<T> response = new RestResponse<T>();
   response.setCode(-1);
   response.setMsg(msg);
   return response;
  }
  public static <T> RestResponse<T> validfail(T result,String msg) {
   RestResponse<T> response = new RestResponse<T>();
   response.setCode(-1);
   response.setResult(result);
   response.setMsg(msg);
   return response;
  }

  public static <T> RestResponse<T> success(T result) {
   RestResponse<T> response = new RestResponse<T>();
   response.setResult(result);
   return response;
  }
  public static <T> RestResponse<T> success(T result,String msg) {
   RestResponse<T> response = new RestResponse<T>();
   response.setResult(result);
   response.setMsg(msg);
   return response;
  }
  public static <T> RestResponse<T> success() {
   return new RestResponse<T>();
  }


  public Boolean isSuccessful() {
   return this.code == 0;
  }

 }