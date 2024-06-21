package com.scau.edu.cn.doctor.mapper;

import com.scau.edu.cn.doctor.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86153
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2024-06-20 14:21:56
* @Entity com.scau.edu.cn.doctor.domain.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    /**
     * 查询指定行数据
     *
     * @param orders 查询条件
     * @return 对象列表
     */
    List<Orders> queryAll(Orders orders);

}




