package com.scau.edu.cn.doctor.service;

import com.scau.edu.cn.doctor.domain.CidetailedReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.edu.cn.doctor.request.CidetailedReportDto;
import com.scau.edu.cn.doctor.util.Result;

import java.util.List;

/**
* @author 86153
* @description 针对表【cidetailed_report】的数据库操作Service
* @createDate 2024-06-20 14:21:56
*/
public interface CidetailedReportService extends IService<CidetailedReport> {

    Result getCidetailedReport(CidetailedReportDto cidetailedReportDto);

    Result updateCidetailedReport(List<CidetailedReportDto> cidetailedReportDto);
}
