package com.zrl.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zrl.reggie.dto.DishDto;
import com.zrl.reggie.dto.SetmealDto;
import com.zrl.reggie.entity.Setmeal;

import java.util.List;

/**
 * @author: zrl
 * @date: 2022/5/10 4:19
 * @description:
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时保存套餐与菜品的关联关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     *删除套餐，同时删除套餐与菜品的关联关系
     * @param ids
     */
    void removeWithDish(List<Long> ids);

    /**
     * 修改套餐，同时修改套餐与菜品的关联关系
     * @param setmealDto
     */
    void updateWithDish(SetmealDto setmealDto);

    SetmealDto getByIdWithDish(Long id);
}
