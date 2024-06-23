package com.scau.edu.cn.doctor.service;

import com.scau.edu.cn.doctor.domain.OverallResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.edu.cn.doctor.util.Result;

/**
* @author 86153
* @description 针对表【overall_result】的数据库操作Service
* @createDate 2024-06-20 14:21:56
*/
public interface OverallResultService extends IService<OverallResult> {

    Result creatOverallResult(OverallResult overallResult);

    Result updateOverallResult(OverallResult overallResult);

    Result deleteOverallResult(Integer orId);
}
