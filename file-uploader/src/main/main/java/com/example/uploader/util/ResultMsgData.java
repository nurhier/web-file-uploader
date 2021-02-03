package com.example.uploader.util;

import lombok.Data;

/**
 * 返回前端的实体对象
 * @author nurhier
 */
@Data
public class ResultMsgData<T>{

    /** 是否成功 1: success, 0: error*/
    private int status;

    /** 消息 .*/
    private String msg;

    /** 具体值 .*/
    private T data;


}
