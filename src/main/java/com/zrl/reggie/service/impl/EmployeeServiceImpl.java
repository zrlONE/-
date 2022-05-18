package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.entity.Employee;
import com.zrl.reggie.mapper.EmployeeMapper;
import com.zrl.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author: zrl
 * @date: 2022/5/5 3:55
 * @description:
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
