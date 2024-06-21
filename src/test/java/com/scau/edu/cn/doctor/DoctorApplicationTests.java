package com.scau.edu.cn.doctor;

import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DoctorApplicationTests {

    @Resource
    private DoctorService doctorService;
    @Test
    void contextLoads() {
        Doctor doctor = new Doctor();
        doctor.setDocCode("ssm");
        doctor.setPassword("123456");
        System.out.println(doctorService.login(doctor));
    }

}
