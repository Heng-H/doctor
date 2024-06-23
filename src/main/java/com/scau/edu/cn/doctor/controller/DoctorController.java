package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.request.DoctorDto;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 验证码登录
     * @param doctorDto
     * @return
     */
    @PostMapping("loginByCode")
    public Result loginByCode (@RequestBody DoctorDto doctorDto) {

        return doctorService.loginByCode(doctorDto);
    }

    /**
     * 注册
     * @param doctorDto
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody DoctorDto doctorDto) {
        return doctorService.register(doctorDto);
    }

    /**
     * 发送验证码
     * @param docId
     * @return
     */
    @GetMapping("sendCode/{docId}")
    public Result sendCode(@PathVariable("docId") String docId) {
        return doctorService.sendCode(docId);
    }

    /**
     * 修改密码发送验证码
     * @param doctorDto
     * @return
     */
    @PostMapping("updatePassword")
    public Result updatePasswordProcess(@RequestBody DoctorDto doctorDto) {
        return doctorService.updatePasswordProcess(doctorDto);
    }
}
