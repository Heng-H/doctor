package com.scau.edu.cn.doctor;

import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.request.UsersDto;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.service.OrdersService;
import com.scau.edu.cn.doctor.service.SetMealService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DoctorApplicationTests {

    @Resource
    private DoctorService doctorService;
    @Resource
    private OrdersService orderService;
    @Resource
    private SetMealService setMealService;
    @Test
    void contextLoads() {
        setMealService.getMealData();
    }

}
