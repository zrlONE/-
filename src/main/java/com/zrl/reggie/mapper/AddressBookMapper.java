package com.zrl.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrl.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.tomcat.jni.Address;

/**
 * @author: zrl
 * @date: 2022/5/15 2:51
 * @description:
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
