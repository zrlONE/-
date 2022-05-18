package com.zrl.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrl.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zrl
 * @date: 2022/5/16 0:36
 * @description:
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
