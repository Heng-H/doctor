package com.scau.edu.cn.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.edu.cn.doctor.domain.Doctor;
import com.scau.edu.cn.doctor.request.DoctorDto;
import com.scau.edu.cn.doctor.service.DoctorService;
import com.scau.edu.cn.doctor.mapper.DoctorMapper;
import com.scau.edu.cn.doctor.util.JwtUtils;
import com.scau.edu.cn.doctor.util.Result;
import com.scau.edu.cn.doctor.util.SendMessage;
import com.scau.edu.cn.doctor.util.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.scau.edu.cn.doctor.util.Status.*;

/**
* @author 86153
* @description 针对表【doctor】的数据库操作Service实现
* @createDate 2024-06-20 14:21:56
*/
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private DoctorMapper doctorMapper;

    /**
     * 医生登录,返回token
     * @param doctorDto
     * @return
     */
    @Override
    public Result login(DoctorDto doctorDto) {
        String docIdOrCode = doctorDto.getDocIdOrCode();
        String password = doctorDto.getPassword();
        //根据docIdOrCode查询医生

        Wrapper<Doctor> wrapper = new QueryWrapper<Doctor>()
                .eq("docId", docIdOrCode).or()
                .eq("docCode", docIdOrCode);

        Doctor doc=this.getOne(wrapper);

        if(doc==null){
            return Result.error(USER_LOGIN_NOT_EXIST);
        }
        else{
            if(!doc.getPassword().equals(password)){
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

        redisTemplate.opsForHash().putAll("doctor:" + token, tokenInfo);
        redisTemplate.expire("doctor:" + token, 1800, TimeUnit.SECONDS);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("doctor", doc);
        System.out.println(result);
        return Result.success(result);
    }

    /**
     * 医生验证码登录
     * @param doctorDto
     * @return
     */
    @Override
    public Result loginByCode(DoctorDto doctorDto) {
        String doctorId = doctorDto.getDocId();
        String code = doctorDto.getCode();
        String codeRedis = (String) redisTemplate.opsForValue().get("doctor:" + doctorId);
        if (codeRedis == null) {
            return Result.error(USER_CAPTCHA_NOT_EXIST);
        }
        if (!code.equals(codeRedis)) {
            return Result.error(USER_CAPTCHA_ERROR);
        }
        Doctor doctor = this.getById(doctorId);
        if(doctor==null){
            return Result.error(USER_LOGIN_NOT_EXIST);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("docId", doctor.getDocId());
        String token = JwtUtils.generateJwt(map);
        // 准备一个HashMap来存储与JWT关联的额外信息到Redis
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("docId", doctor.getDocId()); // 存储用户ID，便于根据token查找用户
        tokenInfo.put("token", token); // 可选，也可以只存储token作为标识
        tokenInfo.put("expiration", String.valueOf(System.currentTimeMillis() + 1800000L)); // 存储JWT的过期时间戳
        // 将token作为键，关联信息作为值存入Redis
        //设计这个键的时间长度，可以根据实际情况设置，比如15分钟，30分钟，1小时，1天等等

        redisTemplate.opsForHash().putAll("doctor:" + token, tokenInfo);
        redisTemplate.expire("doctor:" + token, 1800, TimeUnit.SECONDS);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("doctor", doctor);
        return Result.success(result);
    }

    /**
     * 医生注册
     * @param doctorDto
     * @return
     */
    @Override
    public Result register(DoctorDto doctorDto) {
        String codeRedis = (String) redisTemplate.opsForValue().get(doctorDto.getDocId());
        //验证码校验
        if (codeRedis == null) {
            return Result.error(USER_CAPTCHA_NOT_EXIST);
        }
        if (!doctorDto.getCode().equals(codeRedis)) {
            return Result.error(USER_CAPTCHA_ERROR);
        }
        String DocId = doctorDto.getDocId();
        String DocCode = doctorDto.getDocCode();
        Doctor doctorResult = this.getById(DocId);
        Doctor doctorResult2 = this.getOne(new QueryWrapper<Doctor>().eq("docCode", DocCode));
        //判断该用户是否已经注册
        if (doctorResult != null || doctorResult2 != null) {
            return Result.error(USER_REGISTER_ALREADY_EXIST);
        }
        boolean result = this.save(doctorResult);
        if (result)
            return Result.success(doctorResult);
        else return Result.error(USER_REGISTER_FAILED);
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @Override
    public Result sendCode(String phone,Integer type) throws Exception {
        //先判断该手机号是否已经发送过验证码
        if (redisTemplate.opsForValue().get(phone) != null) {
            return Result.error(USER_CAPTCHA_SEND_TOO_FAST);
        }
        //TODO 发送验证码
        SendMessage sendMessage = new SendMessage();
        String result = sendMessage.message(phone);
        redisTemplate.opsForValue().set(phone, result, 60, TimeUnit.SECONDS);
//        SendSms sendSms = new SendSms();
//       Result result=sendSms.sendSms(phone,type);
//
//        redisTemplate.opsForValue().set(phone, result.getData(), 60, TimeUnit.SECONDS);
        return Result.success();
    }

    /**
     * 修改密码
     * @param doctorDto
     * @return
     */
    @Override
    public Result updatePasswordProcess(DoctorDto doctorDto) {
        String DocId=doctorDto.getDocId();
        String code=doctorDto.getCode();
        Doctor doc = this.getById(DocId);
        if(doc==null){
            return Result.error(USER_UPDATE_PASSWORD_NOT_EXIST);
        }
        String codeRedis = (String) redisTemplate.opsForValue().get(DocId);
        if (codeRedis == null) {
            return Result.error(USER_CAPTCHA_NOT_EXIST);
        }
        if (!code.equals(codeRedis)) {
            return Result.error(USER_CAPTCHA_ERROR);
        }
        doc.setPassword(doctorDto.getPassword());
        boolean result = this.updateById(doc);
        if (result)
            return Result.success(doc);
        else return Result.error(USER_UPDATE_PASSWORD_FAILED);
    }
}




