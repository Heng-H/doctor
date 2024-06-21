package com.scau.edu.cn.doctor.controller;

import com.scau.edu.cn.doctor.domain.Doctor;
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
     * @param doctorId
     *  @param code
     * @return
     */
    @PostMapping("loginByCode")
    public Result loginByCode (@RequestParam String doctorId, @RequestParam String code) {

        return doctorService.loginByCode(doctorId,code);
    }


    /**
     * 注册
     * @param doctor
     * @return
     */
    @PostMapping("register")


    public Result register(@RequestBody Doctor doctor,@RequestParam("code") String code) {
        return doctorService.register(doctor,code);
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("sendCode")
    public Result sendCode(@RequestParam("phone") String phone) {
        return doctorService.sendCode(phone);
    }

    /**
     * 修改密码发送验证码
     * @param Users
     * @param code
     * @return
     */
    @PostMapping("updatePassword")
    public Result updatePasswordProcess(@RequestBody Doctor doctor,@RequestParam("code") String code) {
        return doctorService.updatePasswordProcess(doctor,code);
    }
}
