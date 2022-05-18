package com.zrl.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrl.reggie.entity.Dish;
import com.zrl.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zrl
 * @date: 2022/5/10 4:18
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
