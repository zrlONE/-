package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.entity.Dish;
import com.zrl.reggie.entity.DishFlavor;
import com.zrl.reggie.mapper.DishFlaovrMapper;
import com.zrl.reggie.mapper.DishMapper;
import com.zrl.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author: zrl
 * @date: 2022/5/10 3:43
 * @description:
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlaovrMapper, DishFlavor> implements DishFlavorService {

}
