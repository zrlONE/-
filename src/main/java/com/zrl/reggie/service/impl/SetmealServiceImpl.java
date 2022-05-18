package com.zrl.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrl.reggie.common.R;
import com.zrl.reggie.dto.SetmealDto;
import com.zrl.reggie.entity.Dish;
import com.zrl.reggie.entity.DishFlavor;
import com.zrl.reggie.entity.Setmeal;
import com.zrl.reggie.entity.SetmealDish;
import com.zrl.reggie.mapper.DishMapper;
import com.zrl.reggie.mapper.SetmealMapper;
import com.zrl.reggie.service.DishService;
import com.zrl.reggie.service.SetmealDishService;
import com.zrl.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zrl
 * @date: 2022/5/10 3:43
 * @description:
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;


    /**
     * 新增套餐，同时保存套餐与菜品的关联关系
     * @param setmealDto
     */
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        //保存基本信息,操作setmeal
        this.save(setmealDto);

        //保存菜品与套餐的关联信息，操作setmeal_dish
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     *删除套餐，同时删除套餐与菜品的关联关系
     * @param ids
     */
    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态,确定是否可用删除
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Setmeal::getStatus,1);
        lambdaQueryWrapper.in(Setmeal::getId,ids);

        int count = this.count(lambdaQueryWrapper);
        if(count>0){
            //如果不能删除，抛出一个业务异常
            throw new RuntimeException("套餐正在售卖中不能删除!");
        }

        //如果可以删除，先删除套餐表中的数据
        this.removeByIds(ids);

        //删除关系表中的数据
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        lambdaQueryWrapper1.in(SetmealDish::getSetmealId,ids);

        setmealDishService.remove(lambdaQueryWrapper1);

    }

    /**
     * 更新套餐数据
     * @param setmealDto
     */
    @Transactional
    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //更新Setmeal表基本信息
        this.updateById(setmealDto);

        //清理当前菜品对应口味数据---setmeal_dishes表的delete操作
        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper();
        lqw.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(lqw);

        //添加当前提交过来的口味数据---setmeal_dishes表的insert操作
        List<SetmealDish> dishes = setmealDto.getSetmealDishes();
        dishes = dishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味到表setmeal_dishes
        setmealDishService.saveBatch(dishes);
    }


    /**
     * 回显套餐数据
     * @param id
     * @return
     */
    @Override
    public SetmealDto getByIdWithDish(Long id) {
        //查询基本信息回显
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmeal.getId());
        List<SetmealDish> list = setmealDishService.list(lambdaQueryWrapper);
        setmealDto.setSetmealDishes(list);

        return setmealDto;
    }


}
