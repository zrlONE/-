package com.zrl.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zrl.reggie.entity.Category;

/**
 * @author: zrl
 * @date: 2022/5/10 3:42
 * @description:
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
