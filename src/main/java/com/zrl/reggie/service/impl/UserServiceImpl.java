package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.dto.DishDto;
import com.zrl.reggie.entity.Dish;
import com.zrl.reggie.entity.DishFlavor;
import com.zrl.reggie.entity.User;
import com.zrl.reggie.mapper.DishMapper;
import com.zrl.reggie.mapper.UserMapper;
import com.zrl.reggie.service.DishFlavorService;
import com.zrl.reggie.service.DishService;
import com.zrl.reggie.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zrl
 * @date: 2022/5/10 3:43
 * @description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
