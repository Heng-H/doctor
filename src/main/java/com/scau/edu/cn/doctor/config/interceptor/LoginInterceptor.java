package com.scau.edu.cn.doctor.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.scau.edu.cn.doctor.util.JwtUtils;
import com.scau.edu.cn.doctor.util.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.scau.edu.cn.doctor.util.Status.USER_LOGIN_RELOGIN;


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

 /*   private RedisTemplate<String,Object> redisTemplate;

    public LoginInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }*/

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override //目标资源方法运行前运行, 返回true: 放行, 放回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //设置返回体的类型为json
        resp.setContentType("application/json;charset=UTF-8");
        //3.获取请求头中的令牌（token）。
        String jwt = req.getHeader("Authorization");

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error(USER_LOGIN_RELOGIN);
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            // 设置响应的内容类型和字符编码

            resp.getWriter().write(notLogin);
            return false;
        }
        //从redis中获取用户信息，判断用户是否存在，如果不存在，返回错误结果（未登录）。

        if(!Boolean.TRUE.equals(redisTemplate.hasKey(jwt)))
        {

            log.info("redis中没有该用户信息, 返回未登录信息");
            Result error = Result.error(USER_LOGIN_RELOGIN);
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            //

            //判断这个键的过期时间是否还剩下5分钟，如果过了，则删除这个键
            if(System.currentTimeMillis()+300000L > claims.getExpiration().getTime()) {
                String userId = (String) claims.get("docId");
                Map<String, Object> map = new HashMap<>();
                map.put("docId", userId);
                String newToken = JwtUtils.generateJwt(map);
                // 准备一个HashMap来存储与JWT关联的额外信息到Redis
                Map<String, String> tokenInfo = new HashMap<>();
                tokenInfo.put("docId", userId); // 存储用户ID，便于根据token查找用户
                tokenInfo.put("token", newToken); // 可选，也可以只存储token作为标识
                tokenInfo.put("expiration", String.valueOf(System.currentTimeMillis() + 1800000L)); // 存储JWT的过期时间戳
                // 将token作为键，关联信息作为值存入Redis
                //设计这个键的时间长度，可以根据实际情况设置，比如15分钟，30分钟，1小时，1天等等
                redisTemplate.opsForHash().putAll(newToken, tokenInfo);
                redisTemplate.expire(newToken, 1800, TimeUnit.SECONDS);

            resp.addHeader("Authorization", newToken);
            }
            else {
                resp.addHeader("Authorization", jwt);
            }

        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error(USER_LOGIN_RELOGIN);
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        //检查token是否过期，

        //6.放行。
        log.info("令牌合法, 放行");

        return true;
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");
    }

    @Override //视图渲染完毕后运行, 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}