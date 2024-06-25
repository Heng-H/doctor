package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.OverallResult;
import com.scau.edu.cn.doctor.service.OverallResultService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("overallResult")
public class OverallResultController {
    /**
     * 服务对象
     */
    @Resource
    private OverallResultService overallResultService;

    /**
     * 新增总检结论项
     * @param overallResult
     * @return
     */
    @PostMapping("creatOverallResult")
    public Result creatOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.creatOverallResult(overallResult);
    }

    /**
     * 修改总检结论项
     * @param overallResult
     * @return
     */
    @PostMapping("updateOverallResult")
    public Result updateOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.updateOverallResult(overallResult);
    }

    /**
     * 删除总检结论项
     * @param orId
     * @return
     */
    @PostMapping("deleteOverallResult/{orId}")
    public Result deleteOverallResult(@PathVariable Integer orId) {
        return overallResultService.deleteOverallResult(orId);
    }


}
