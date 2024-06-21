package com.scau.edu.cn.doctor.service;

import com.scau.edu.cn.doctor.domain.Doctor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scau.edu.cn.doctor.request.DoctorDto;
import com.scau.edu.cn.doctor.util.Result;

/**
* @author 86153
* @description 针对表【doctor】的数据库操作Service
* @createDate 2024-06-20 14:21:56
*/
public interface DoctorService extends IService<Doctor> {

    Result login(Doctor doctor);

    Result loginByCode(DoctorDto doctorDto);

    Result register(DoctorDto doctorDto);

    Result sendCode(String phone);

    Result updatePasswordProcess(DoctorDto doctorDto);
}
