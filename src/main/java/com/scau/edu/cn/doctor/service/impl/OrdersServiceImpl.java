package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.domain.Orders;
import com.scau.edu.cn.doctor.domain.SetMeal;
import com.scau.edu.cn.doctor.domain.Users;
import com.scau.edu.cn.doctor.mapper.UsersMapper;
import com.scau.edu.cn.doctor.request.UsersDto;
import com.scau.edu.cn.doctor.response.OrderInfoResponse;
import com.scau.edu.cn.doctor.service.HospitalService;
import com.scau.edu.cn.doctor.service.OrdersService;
import com.scau.edu.cn.doctor.mapper.OrdersMapper;
import com.scau.edu.cn.doctor.service.SetMealService;
import com.scau.edu.cn.doctor.service.UsersService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.scau.edu.cn.doctor.util.Status.CIREPORT_ARCHIVE_FAILED;
import static com.scau.edu.cn.doctor.util.Status.ORDER_FIND_NOT_EXIST;

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
    @Resource
    private HospitalService hospitalService;
    @Resource
    private SetMealService setMealService;
    @Resource
    private UsersService usersService;
    @Resource
    private UsersMapper usersMapper;

    @Override
    public Result<Page<OrderInfoResponse>> checkOrder(UsersDto usersDto) {
        Orders order = new Orders();
        Users user = new Users();
        List<Orders> ordersList = new ArrayList<>();
        List<Users> usersList = usersMapper.queryAll(user);
        order.setState(usersDto.getState());
        order.setOrderDate(usersDto.getOrderDate());
        order.setSmId(usersDto.getSmId());
        if(usersList.size() == 0||usersList==null){
            return Result.error(ORDER_FIND_NOT_EXIST);
        }else{
            for(Users users:usersList){
                order.setUserId(users.getUserId());
                ordersList.addAll(ordersMapper.queryAll(order));
            }
        }
        if(ordersList.size() == 0||ordersList==null)
        {
            return Result.error(ORDER_FIND_NOT_EXIST);
        }
        Page<OrderInfoResponse> orderInfoResponses = new Page<>(usersDto.getPage(), 2);
        for(Orders orders:ordersList){
            OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
            orderInfoResponse.setOrderId(orders.getOrderId());
            orderInfoResponse.setOrderDate(orders.getOrderDate());
            orderInfoResponse.setHpName(hospitalService.getById(orders.getHpId()).getName());
            orderInfoResponse.setSmName(setMealService.getById(orders.getSmId()).getName());
            orderInfoResponse.setUserId(orders.getUserId());
            orderInfoResponse.setSex(usersService.getById(orders.getUserId()).getUserId());
            orderInfoResponse.setRealName(usersService.getById(orders.getUserId()).getRealName());
            orderInfoResponses.getRecords().add(orderInfoResponse);
        }

        return Result.success(orderInfoResponses);
    }

    @Override
    public Result updateState(String ordersId) {
        Orders orders = this.getById(ordersId);
        orders.setState(2);
        boolean result = this.updateById(orders);
        if(result){
            return Result.success();
        }else{
            return Result.error(CIREPORT_ARCHIVE_FAILED);
        }
    }


}




