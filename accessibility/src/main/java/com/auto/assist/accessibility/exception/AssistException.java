package com.auto.assist.accessibility.exception;

public class AssistException extends Exception
{



    private int retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public AssistException() {
        super();
    }

    public AssistException(String message) {
        super(message);
        msgDes = message;
    }

    public AssistException(int retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public int getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }

}
