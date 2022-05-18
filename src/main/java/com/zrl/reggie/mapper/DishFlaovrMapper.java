package com.zrl.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrl.reggie.entity.Dish;
import com.zrl.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zrl
 * @date: 2022/5/10 4:18
 * @description:
 */
@Mapper
public interface DishFlaovrMapper extends BaseMapper<DishFlavor> {
}
