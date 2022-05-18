package com.zrl.reggie.common;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录者的id
 * @author: zrl
 * @date: 2022/5/9 12:42
 * @description:
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setId(Long id){
        threadLocal.set(id);
    }

    public static Long getId(){
        return threadLocal.get();
    }
}
