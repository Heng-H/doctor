package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.mapper.DoctorMapper;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.scau.edu.cn.doctor.util.Status.USER_LOGIN_NOT_EXIST;
import static com.scau.edu.cn.doctor.util.Status.USER_LOGIN_PASSWORD_ERROR;

/**
* @author 86153
* @description 针对表【doctor】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService{

    @Resource
    private DoctorMapper doctorMapper;
    @Override
    public Result login(Doctor doctor) {
        Doctor doc = null;
        if(doctor.getDocCode()!=null) {
            doc = doctorMapper.selectOne(new QueryWrapper<Doctor>().eq("docCode", doctor.getDocCode()));
        }
        else{
            doc = doctorMapper.selectById(doctor.getDocId());
        }

        if(doc==null){
            return Result.error(USER_LOGIN_NOT_EXIST);
        }
        else{
            if(!doc.getPassword().equals(doctor.getPassword())){
                return Result.error(USER_LOGIN_PASSWORD_ERROR);
            }
        }
        return Result.success(doc);
    }
}




