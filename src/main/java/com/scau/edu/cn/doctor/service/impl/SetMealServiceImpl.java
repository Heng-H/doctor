package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.SetMeal;
import com.scau.edu.cn.doctor.service.SetMealService;
import com.scau.edu.cn.doctor.mapper.SetMealMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.scau.edu.cn.doctor.util.Status.SETMEAL_IS_EMPTY;

/**
* @author 86153
* @description 针对表【set_meal】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, SetMeal>
    implements SetMealService{

    @Override
    public Result<List<SetMeal>> checkSetMeal() {
        List<SetMeal> setMeals = this.list();
        if(setMeals == null || setMeals.size() == 0)
            return Result.error(SETMEAL_IS_EMPTY);
        return Result.success(setMeals);
    }
}




