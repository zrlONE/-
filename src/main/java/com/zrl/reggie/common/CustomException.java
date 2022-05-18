package com.zrl.reggie.common;

/**
 * 自定义异常类
 * @author: zrl
 * @date: 2022/5/10 4:34
 * @description:
 */
public class CustomException extends RuntimeException {

    public  CustomException (String message){
        super(message);
    }
}
