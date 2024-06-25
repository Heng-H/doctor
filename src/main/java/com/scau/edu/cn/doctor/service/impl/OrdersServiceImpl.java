package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

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
    public Result<Map<String, Object>> checkOrder(UsersDto usersDto) {

        Users user = new Users();
        user.setRealName(usersDto.getRealName());
        user.setUserId(usersDto.getUserId());



        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(usersDto.getRealName()!=null, "realName", usersDto.getRealName())
                    .like(usersDto.getUserId()!=null, "userId", usersDto.getUserId())
                    .eq(usersDto.getSex()!=null, "sex", usersDto.getSex());


        List<Users> usersList = usersMapper.selectList(queryWrapper)


                ;

      /*  List<Users> usersList1 = usersMapper.queryAll(user);*/
        List<String> userIdList = new ArrayList<>();

        if(usersList.size() == 0||usersList==null){
            return Result.error(ORDER_FIND_NOT_EXIST);
        }else{
            for(Users users:usersList){

                userIdList.add(users.getUserId());
            }
        }

        Page<Orders> ordersPage = new Page<>(usersDto.getPage(), usersDto.getPageSize());
    System.out.println(usersDto.getPage());
        //假如orders有值，是为查找的条件



        QueryWrapper<Orders> queryWrapperlist = new QueryWrapper<>();
        queryWrapperlist.eq(usersDto.getState()!=null, "state", usersDto.getState())

                        .eq(usersDto.getOrderDate()!=null, "orderDate", usersDto.getOrderDate())
                        .eq(usersDto.getSmId()!=null, "smId", usersDto.getSmId())
                                .in(userIdList.size()!=0, "userId", userIdList)
                           .ne("state", 0);
        queryWrapperlist.orderByDesc("orderDate");

        IPage<Orders> orderIPage = ordersMapper.selectPage(ordersPage, queryWrapperlist);
        if(orderIPage.getRecords().size() == 0){
            return Result.error(ORDER_FIND_NOT_EXIST);
        }
            System.out.println(orderIPage.getRecords());

            List<OrderInfoResponse> orderInfoResponses = new ArrayList<>();
            for(Orders orders:orderIPage.getRecords()){
                OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
                orderInfoResponse.setOrderId(orders.getOrderId());
                orderInfoResponse.setOrderDate(orders.getOrderDate());

                Users users = usersService.getById(orders.getUserId());
                orderInfoResponse.setRealName(users.getRealName());
                orderInfoResponse.setUserId(users.getUserId());
                orderInfoResponse.setSex(users.getSex());

                orderInfoResponse.setSmName(setMealService.getById(orders.getSmId()).getName());
                orderInfoResponse.setHpName(hospitalService.getById(orders.getHpId()).getName());

                orderInfoResponses.add(orderInfoResponse);
            }

            Map<String, Object> map = new HashMap<>();
            map.put("total", orderIPage.getTotal());
            map.put("list", orderInfoResponses);
            map.put("totalPage", ordersPage.getPages());

            return Result.success(map);

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




