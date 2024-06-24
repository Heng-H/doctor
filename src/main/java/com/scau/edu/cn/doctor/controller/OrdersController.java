package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.Orders;
import com.scau.edu.cn.doctor.request.UsersDto;
import com.scau.edu.cn.doctor.service.OrdersService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 查询订单
     * @param usersDto
     * @return
     */
    @PostMapping("checkOrder")
    public Result checkOrder(@RequestBody UsersDto usersDto) {
        return ordersService.checkOrder(usersDto);
    }

    /**
     * 体检报告归档
     * @param ordersId
     * @return
     */
    @GetMapping("updateState/{ordersId}")
    public Result updateState(@PathVariable String ordersId) {
        return ordersService.updateState(ordersId);
    }
}
