package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Users;
import com.scau.edu.cn.doctor.service.UsersService;
import com.scau.edu.cn.doctor.mapper.UsersMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

/**
* @author 86153
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Override
    public Result login(Users user) {
        return null;
    }
}




