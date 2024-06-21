package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Orders;
import com.scau.edu.cn.doctor.service.OrdersService;
import com.scau.edu.cn.doctor.mapper.OrdersMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86153
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public Result checkOrder(Orders order) {
        System.out.println(order);
        List<Orders> orderList = ordersMapper.queryAll(order);
        System.out.println(orderList);
        return null;
    }
}




