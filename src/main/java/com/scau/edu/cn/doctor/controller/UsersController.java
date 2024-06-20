package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.Users;
import com.scau.edu.cn.doctor.service.UsersService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("users")
public class UsersController {

    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public Result login (@RequestBody Users user) {

        return usersService.login(user);
    }
}
