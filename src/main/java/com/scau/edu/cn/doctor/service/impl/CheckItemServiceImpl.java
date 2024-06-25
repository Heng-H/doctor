package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.CheckItem;
import com.scau.edu.cn.doctor.domain.Cireport;
import com.scau.edu.cn.doctor.domain.OverallResult;
import com.scau.edu.cn.doctor.domain.SetMealDetailed;
import com.scau.edu.cn.doctor.service.*;
import com.scau.edu.cn.doctor.mapper.CheckItemMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scau.edu.cn.doctor.util.Status.ORDER_FIND_NOT_EXIST;

/**
* @author 86153
* @description 针对表【check_item】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem>
    implements CheckItemService{

    @Resource
    private CireportService cireportService;
    @Resource
    private OverallResultService overallResultService;

    @Override
    public Result getCheckItem(Integer orderId) {
        Map<String,Object> map = new HashMap<>();
        List<Cireport> cireportList = cireportService.list(new QueryWrapper<Cireport>().eq("orderId", orderId));
        if(cireportList.size() == 0||cireportList == null){
            return Result.error(ORDER_FIND_NOT_EXIST);
        }
        map.put("cireportList",cireportList);
        List<OverallResult> overallResultList = overallResultService.list(new QueryWrapper<OverallResult>().eq("orderId", orderId));
        if(overallResultList.size() == 0||overallResultList == null){
            return Result.error(ORDER_FIND_NOT_EXIST);
        }
        map.put("overall",overallResultList);
        return Result.success(map);
    }


}




