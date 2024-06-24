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
    public Result updateCidetailedReport(CidetailedReportDto cidetailedReportDto) {
        CidetailedReport cidetailedReport = this.getOne(new QueryWrapper<CidetailedReport>().eq("orderId", cidetailedReportDto.getOrderId()).eq("ciId", cidetailedReportDto.getCiId()).eq("name", cidetailedReportDto.getName()));
        if(cidetailedReport == null||cidetailedReport.getCidrId() == null){
            return Result.error(ORDER_FIND_NOT_EXIST);
        }
        cidetailedReport.setValue(cidetailedReportDto.getValue());
        if(cidetailedReport.getType()==1){
            if(Double.parseDouble(cidetailedReportDto.getValue())<cidetailedReport.getMinrange()||Double.parseDouble(cidetailedReportDto.getValue())>cidetailedReport.getMaxrange()){
                cidetailedReport.setIsError(1);
            }
        }
        boolean result=this.saveOrUpdate(cidetailedReport);
        if(!result){
            return Result.error(CIREPORT_SAVE_FAILED);
        }
        return Result.success();
    }


}




