package com.zrl.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrl.reggie.common.BaseContext;
import com.zrl.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zrl
 * @date: 2022/5/10 4:18
 * @description:
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
