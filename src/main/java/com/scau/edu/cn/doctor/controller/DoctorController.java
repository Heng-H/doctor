package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    /**
     * 服务对象
     */
    @Resource
    private DoctorService doctorService;

    /**
     * 登录
     * @param doctor
     * @return
     */
    @PostMapping("login")
    public Result login (@RequestBody Doctor doctor) {

        return doctorService.login(doctor);
    }


}
