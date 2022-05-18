package com.zrl.reggie.cotroller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zrl.reggie.common.BaseContext;
import com.zrl.reggie.common.R;
import com.zrl.reggie.entity.ShoppingCart;
import com.zrl.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: zrl
 * @date: 2022/5/15 20:56
 * @description:
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
         //设置用户id，指定当前是哪个用户的购物车数据
        Long id = BaseContext.getId();
        shoppingCart.setUserId(id);

        //查询当前菜品或者套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,id);

        if(dishId!=null){
            //添加的是菜品
            lambdaQueryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else{
            //添加的是套餐
            lambdaQueryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        //根据userID和(菜品id或者套餐ID)查询购物车中是否已存在此菜品或套餐
        ShoppingCart cartOne = shoppingCartService.getOne(lambdaQueryWrapper);
        if(cartOne!=null){
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartOne.getNumber();
            cartOne.setNumber(number+1);
            shoppingCartService.updateById(cartOne);
        }else {
            //如果不存在，则添加到购物车，数量默认就是一
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            cartOne = shoppingCart;
        }
        return R.success(cartOne);
    }

    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){
        //设置用户id，指定当前是哪个用户的购物车数据
        Long id = BaseContext.getId();
        shoppingCart.setUserId(id);

        //查询当前菜品或者套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,id);

        if(dishId!=null){
            //购物车里的是菜品
            lambdaQueryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else{
            //购物车里的是套餐
            lambdaQueryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        //根据userID和(菜品id或者套餐ID)查询购物车中是否已存在此菜品或套餐
        ShoppingCart cartOne = shoppingCartService.getOne(lambdaQueryWrapper);

        if(cartOne.getNumber()>=1){
            //如果已经存在，就在原来数量基础上减一
            Integer number = cartOne.getNumber();
            cartOne.setNumber(number-1);
            shoppingCartService.updateById(cartOne);
        }
        if(cartOne.getNumber()<=0){
            shoppingCartService.remove(lambdaQueryWrapper);
        }

        return R.success("操作成功");
    }

    /**
     * 查询购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Long id = BaseContext.getId();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,id);
        lambdaQueryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(lambdaQueryWrapper);

        return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> delete(){
        Long id = BaseContext.getId();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,id);
        shoppingCartService.remove(lambdaQueryWrapper);
        return R.success("清空购物车成功");
    }
}
