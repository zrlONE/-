package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.entity.AddressBook;
import com.zrl.reggie.mapper.AddressBookMapper;
import com.zrl.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author: zrl
 * @date: 2022/5/15 2:52
 * @description:
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
