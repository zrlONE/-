package com.zrl.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zrl.reggie.entity.Orders;

/**
 * @author: zrl
 * @date: 2022/5/16 0:38
 * @description:
 */
public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
