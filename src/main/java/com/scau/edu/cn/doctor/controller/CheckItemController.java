package com.scau.edu.cn.doctor.controller;


import com.scau.edu.cn.doctor.service.CheckItemService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("checkItem")
public class CheckItemController {
    /**
     * 服务对象
     */
    @Resource
    private CheckItemService checkItemService;

    @PostMapping("getCheckItem")
    public Result getCheckItem(@RequestBody String orderId){
        return checkItemService.getCheckItem(orderId);
    }
}
