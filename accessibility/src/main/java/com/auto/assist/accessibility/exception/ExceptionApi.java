package com.auto.assist.accessibility.exception;

/**
 * 异常类
 */
public class ExceptionApi
{

    public static final int NODE_NOT_FOUND = 0; //节点找不到




    public static void NODE_NOT_FOUND(String msg) throws AssistException {

        throw new AssistException(NODE_NOT_FOUND, msg);
    }




}
