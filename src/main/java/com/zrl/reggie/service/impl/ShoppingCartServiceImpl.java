package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.entity.ShoppingCart;
import com.zrl.reggie.mapper.ShoppingCartMapper;
import com.zrl.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author: zrl
 * @date: 2022/5/15 20:54
 * @description:
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
