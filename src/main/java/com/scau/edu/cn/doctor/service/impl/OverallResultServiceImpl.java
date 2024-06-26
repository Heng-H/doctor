package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.OverallResult;
import com.scau.edu.cn.doctor.service.OverallResultService;
import com.scau.edu.cn.doctor.mapper.OverallResultMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.scau.edu.cn.doctor.util.Status.*;

/**
* @author 86153
* @description 针对表【overall_result】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class OverallResultServiceImpl extends ServiceImpl<OverallResultMapper, OverallResult>
    implements OverallResultService{
    @Override
    public Result creatOverallResult(OverallResult overallResult) {
        boolean result=this.save(overallResult);
        if(!result){

            return Result.error(CIREPORT_SAVE_CONCLUSION_FAILED);
        }
        else{

            return Result.success(overallResult);
        }
    }

    @Override
    public Result updateOverallResult(OverallResult overallResult) {
        boolean result=this.saveOrUpdate(overallResult);

        if(!result){
            return Result.error(CIREPORT_UPDATE_CONCLUSION_FAILED);
        }
        else{
            return Result.success();
        }
    }

    @Override
    public Result deleteOverallResult(Integer orId) {
            boolean result=this.removeById(orId);
            if(!result){
                return Result.error(CIREPORT_UPDATE_CONCLUSION_FAILED);
            }
            else{
                return Result.success();
            }
    }
}




