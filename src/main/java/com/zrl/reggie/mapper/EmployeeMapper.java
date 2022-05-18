package com.zrl.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrl.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zrl
 * @date: 2022/5/5 3:52
 * @description:
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
