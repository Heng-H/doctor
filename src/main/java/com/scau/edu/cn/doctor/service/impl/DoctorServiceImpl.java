package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.mapper.DoctorMapper;
import com.scau.edu.cn.doctor.util.JwtUtils;
import com.scau.edu.cn.doctor.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private DoctorService doctorService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 医生登录,返回token
     * @param doctor
     * @return
     */
    @Override
    public Result login(Doctor doctor) {
        Doctor doc = null;
        if(doctor.getDocCode()!=null) {
            doc = doctorService.getOne(new QueryWrapper<Doctor>().eq("docCode", doctor.getDocCode()));
        }
        else{
            doc = doctorService.getById(doctor.getDocId());
        }

        if(doc==null){
            return Result.error(USER_LOGIN_NOT_EXIST);
        }
        else{
            if(!doc.getPassword().equals(doctor.getPassword())){
                return Result.error(USER_LOGIN_PASSWORD_ERROR);
            }
        }
        //创建redis缓存,放token令牌

        Map<String, Object> map = new HashMap<>();
        map.put("docId", doc.getDocId());
        String token = JwtUtils.generateJwt(map);
        // 准备一个HashMap来存储与JWT关联的额外信息到Redis
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("docId", doc.getDocId()); // 存储用户ID，便于根据token查找用户
        tokenInfo.put("token", token); // 可选，也可以只存储token作为标识
        tokenInfo.put("expiration", String.valueOf(System.currentTimeMillis() + 1800000L)); // 存储JWT的过期时间戳
        // 将token作为键，关联信息作为值存入Redis
        //设计这个键的时间长度，可以根据实际情况设置，比如15分钟，30分钟，1小时，1天等等

        redisTemplate.opsForHash().putAll(token, tokenInfo);
        redisTemplate.expire(token, 1800, TimeUnit.SECONDS);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("doctor", doc);
        System.out.println(result);
        return Result.success(result);
    }
}




