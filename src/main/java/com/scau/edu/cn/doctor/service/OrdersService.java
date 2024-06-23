package com.scau.edu.cn.doctor.service;

import com.scau.edu.cn.doctor.domain.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.edu.cn.doctor.request.UsersDto;
import com.scau.edu.cn.doctor.util.Result;

/**
* @author 86153
* @description 针对表【orders】的数据库操作Service
* @createDate 2024-06-20 14:21:56
*/
public interface OrdersService extends IService<Orders> {

    Result checkOrder(UsersDto usersDto);
}
