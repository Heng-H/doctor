package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.request.CidetailedReportDto;
import com.scau.edu.cn.doctor.service.CidetailedReportService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cidetailedReport")
public class CidetailedReportController {
    /**
     * 服务对象
     */
    @Resource
    private CidetailedReportService cidetailedReportService;

    /**
     * 获取检查项明细
     * @param cidetailedReportDto
     * @return
     */
    @PostMapping("getCidetailedReport")
    public Result getCidetailedReport(@RequestBody CidetailedReportDto cidetailedReportDto)
    {
        return cidetailedReportService.getCidetailedReport(cidetailedReportDto);
    }

    /**
     * 更新检查项明细
     * @param cidetailedReportDto
     * @return
     */
    @PostMapping("updateCidetailedReport")
    public Result updateCidetailedReport(@RequestBody CidetailedReportDto cidetailedReportDto)
    {
        return cidetailedReportService.updateCidetailedReport(cidetailedReportDto);
    }

}
