package com.zrl.reggie.dto;

import com.zrl.reggie.entity.Setmeal;
import com.zrl.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
