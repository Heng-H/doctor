package com.scau.edu.cn.doctor.controller;


import com.scau.edu.cn.doctor.service.CheckItemService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("checkItem")
public class CheckItemController {
    /**
     * 服务对象
     */
    @Resource
    private CheckItemService checkItemService;

    /**
     * 获取检查项
     * @param orderId
     * @return
     */
    @GetMapping("getCheckItem/{orderId}")
    public Result getCheckItem(@PathVariable("orderId") Integer orderId){
        return checkItemService.getCheckItem(orderId);
    }
}
