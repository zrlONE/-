package com.zrl.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zrl.reggie.dto.DishDto;
import com.zrl.reggie.entity.Dish;

/**
 * @author: zrl
 * @date: 2022/5/10 4:19
 * @description:
 */
public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);

    void deleteWithFlavor(Long id);
}
