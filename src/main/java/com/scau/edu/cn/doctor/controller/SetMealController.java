package com.scau.edu.cn.doctor.controller;
import com.scau.edu.cn.doctor.service.SetMealService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * (SetMeal)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("setMeal")
public class SetMealController {
        /**
         * 服务对象
         */
        @Resource
        private SetMealService setMealService;

        /**
         * 查询套餐
         * @return
         */
        @GetMapping("checkSetMeal")
        public Result checkSetMeal() {
                return setMealService.checkSetMeal();
        }

        @GetMapping("getMealData")
        public Result  getMealData(){
                return setMealService.getMealData();
                }
}


