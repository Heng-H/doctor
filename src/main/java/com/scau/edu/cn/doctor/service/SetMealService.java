package com.scau.edu.cn.doctor.service;

import com.scau.edu.cn.doctor.domain.SetMeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.edu.cn.doctor.util.Result;

/**
* @author 86153
* @description 针对表【set_meal】的数据库操作Service
* @createDate 2024-06-20 14:21:56
*/
public interface SetMealService extends IService<SetMeal> {

    /**
     * 查询套餐
     * @return
     */
    Result checkSetMeal();

    Result getMealData();
}
