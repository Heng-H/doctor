package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.Orders;
import com.scau.edu.cn.doctor.request.UsersDto;
import com.scau.edu.cn.doctor.service.OrdersService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
