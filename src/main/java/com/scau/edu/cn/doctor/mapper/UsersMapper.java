package com.scau.edu.cn.doctor.mapper;

import com.scau.edu.cn.doctor.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 86153
* @description 针对表【users】的数据库操作Mapper
* @createDate 2024-06-20 14:21:56
* @Entity com.scau.edu.cn.doctor.domain.Users
*/
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 查询指定行数据
     *
     * @param users 查询条件
     * @return 对象列表
     */
    List<Users> queryAll(Users users);
}




