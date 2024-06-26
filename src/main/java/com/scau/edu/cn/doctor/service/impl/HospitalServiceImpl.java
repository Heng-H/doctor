package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Hospital;
import com.scau.edu.cn.doctor.service.HospitalService;
import com.scau.edu.cn.doctor.mapper.HospitalMapper;
import com.scau.edu.cn.doctor.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 86153
* @description 针对表【hospital】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital>
    implements HospitalService{

}




