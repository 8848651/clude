package com.base.xczx_plus_base.base.exception;
public class XczxPlusException extends RuntimeException {

    private String errMessage;

    public XczxPlusException() {
    }

    public XczxPlusException(String message) {
        super(message);
        this.errMessage = message;

    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public static void cast(String message){
        throw new XczxPlusException(message);
    }

}
