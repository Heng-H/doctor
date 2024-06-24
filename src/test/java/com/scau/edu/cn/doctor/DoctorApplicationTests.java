package com.scau.edu.cn.doctor;

import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.request.UsersDto;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DoctorApplicationTests {

    @Resource
    private DoctorService doctorService;
    @Resource
    private OrdersService orderService;
    @Test
    void contextLoads() {
        UsersDto usersDto = new UsersDto();
        usersDto.setPage(1);

       orderService.checkOrder(usersDto);
    }

}
