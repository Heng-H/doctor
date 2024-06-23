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
@RequestMapping("cidetailedReport")
public class CidetailedReportController {
    /**
     * 服务对象
     */
    @Resource
    private CidetailedReportService cidetailedReportService;

    @PostMapping("getCidetailedReport")
    public Result getCidetailedReport(@RequestBody CidetailedReportDto cidetailedReportDto)
    {
        return cidetailedReportService.getCidetailedReport(cidetailedReportDto);
    }

    @PostMapping("setCidetailedReport")
    public Result setCidetailedReport(@RequestBody CidetailedReportDto cidetailedReportDto)
    {
        return cidetailedReportService.setCidetailedReport(cidetailedReportDto);
    }

}
