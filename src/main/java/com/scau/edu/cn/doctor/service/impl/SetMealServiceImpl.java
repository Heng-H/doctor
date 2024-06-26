package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Orders;
import com.scau.edu.cn.doctor.domain.SetMeal;
import com.scau.edu.cn.doctor.mapper.OrdersMapper;
import com.scau.edu.cn.doctor.service.OrdersService;
import com.scau.edu.cn.doctor.service.SetMealService;
import com.scau.edu.cn.doctor.mapper.SetMealMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scau.edu.cn.doctor.util.Status.SETMEAL_IS_EMPTY;

/**
* @author 86153
* @description 针对表【set_meal】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, SetMeal>
    implements SetMealService{
   /* @Resource
    private OrdersServiceImpl ordersService;*/
    @Override
    public Result<List<SetMeal>> checkSetMeal() {
        List<SetMeal> setMeals = this.list();
        if(setMeals == null || setMeals.size() == 0)
            return Result.error(SETMEAL_IS_EMPTY);
        return Result.success(setMeals);
    }
@Resource
  private OrdersMapper ordersMapper;
    @Override
    public Result getMealData()
    {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now(); // 获取当前日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 日期格式化
        for(int i=0; i<7; i++){
            dateList.add(currentDate.minusDays(6-i)); // 往回减去天数
        }
        List<SetMeal> setMeals = this.list();
        List<String> mealNames = new ArrayList<>();
        for(SetMeal setMeal : setMeals){
            String Name = setMeal.getName();
            mealNames.add(Name);
        }


       List<Map<String, Object>> mapList =  new ArrayList<>();
        for(SetMeal setMeal : setMeals){
            Map<String, Object> maps = new HashMap<>();
            maps.put("name", setMeal.getName());
            List<Map<String, Object>> map = new ArrayList<>();
             for(LocalDate date : dateList) {
                 QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
                 queryWrapper.eq("smId", setMeal.getSmId());
                 queryWrapper.eq("orderDate", date);
                 queryWrapper.ne("state", 0);
                 Long count = ordersMapper.selectCount(queryWrapper);
                 Map<String, Object> countMap = new HashMap<>();
                 countMap.put("date",date);
                 countMap.put("count", count);
                 map.add(countMap);
             }
             maps.put("data", map);
             mapList.add(maps);
        }

        return Result.success(mapList);
    }
}




