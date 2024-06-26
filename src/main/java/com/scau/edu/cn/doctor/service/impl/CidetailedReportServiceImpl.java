package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.CidetailedReport;
import com.scau.edu.cn.doctor.request.CidetailedReportDto;
import com.scau.edu.cn.doctor.service.CidetailedReportService;
import com.scau.edu.cn.doctor.mapper.CidetailedReportMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.scau.edu.cn.doctor.util.Status.CIREPORT_SAVE_FAILED;
import static com.scau.edu.cn.doctor.util.Status.ORDER_FIND_NOT_EXIST;

/**
* @author 86153
* @description 针对表【cidetailed_report】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class CidetailedReportServiceImpl extends ServiceImpl<CidetailedReportMapper, CidetailedReport>
    implements CidetailedReportService{

    @Override
    public Result getCidetailedReport(CidetailedReportDto cidetailedReportDto) {
        List<CidetailedReport> cidetailedReportList = this.list(new QueryWrapper<CidetailedReport>()
                .eq("orderId", cidetailedReportDto.getOrderId())
                .eq("ciId", cidetailedReportDto.getCiId()));
        if (cidetailedReportList.size() == 0|| cidetailedReportList == null) {
            return Result.error(ORDER_FIND_NOT_EXIST);
        } else {
            return Result.success(cidetailedReportList);
        }
    }

    @Override
    public Result updateCidetailedReport(List<CidetailedReportDto> cidetailedReportDto) {
        for (CidetailedReportDto report : cidetailedReportDto) {
            CidetailedReport cidetailedReport = this.getOne(new QueryWrapper<CidetailedReport>().eq("orderId", report.getOrderId()).eq("ciId", report.getCiId()).eq("name", report.getName()));
            if (cidetailedReport == null || cidetailedReport.getCidrId() == null) {
                return Result.error(ORDER_FIND_NOT_EXIST);
            }
            cidetailedReport.setValue(report.getValue());
            if (cidetailedReport.getType() == 1) {
                Double value;
        try{
         value = Double.parseDouble(report.getValue());
        }catch (Exception e){
            return Result.error("范围为数值的报告值只能为数字");
        }
                if (value < cidetailedReport.getMinrange() || value > cidetailedReport.getMaxrange()) {
                    cidetailedReport.setIsError(1);
                }else{
                    cidetailedReport.setIsError(0);
                }
            }else if(cidetailedReport.getType() == 2){
                if(Objects.equals(cidetailedReport.getValue(), "+") || Objects.equals(cidetailedReport.getValue(), "-")) {
                    if (!cidetailedReport.getValue().equals(cidetailedReport.getNormalValue())) {
                        cidetailedReport.setIsError(1);
                    } else {
                        cidetailedReport.setIsError(0);
                    }
                }else{
                    return Result.error("范围为-的报告值只能为+或-");
                }
            }
            boolean result = this.saveOrUpdate(cidetailedReport);

            if (!result) {
                return Result.error(CIREPORT_SAVE_FAILED);
            }
        }
        return Result.success();
    }


}




