package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.entity.OrderDetail;
import com.zrl.reggie.mapper.OrderDetailMapper;
import com.zrl.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author: zrl
 * @date: 2022/5/16 0:39
 * @description:
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
