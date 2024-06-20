package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.mapper.DoctorMapper;
import org.springframework.stereotype.Service;

/**
* @author 86153
* @description 针对表【doctor】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService{

}




