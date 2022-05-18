package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.common.CustomException;
import com.zrl.reggie.entity.Category;
import com.zrl.reggie.entity.Dish;
import com.zrl.reggie.entity.Setmeal;
import com.zrl.reggie.mapper.CategoryMapper;
import com.zrl.reggie.service.CategoryService;
import com.zrl.reggie.service.DishService;
import com.zrl.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zrl
 * @date: 2022/5/10 3:43
 * @description:
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类。删除前进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {

        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> dishlqw = new LambdaQueryWrapper<>();
        dishlqw.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishlqw);
        if(count>0){
            throw new CustomException("当前分类项关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmeallqw = new LambdaQueryWrapper<>();
        setmeallqw.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmeallqw);
        if(count2>0){
            throw new CustomException("当前分类项关联了套餐，不能删除");
        }

        //正常删除
        super.removeById(id);
    }
}
