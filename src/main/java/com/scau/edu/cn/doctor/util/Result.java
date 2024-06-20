package com.scau.edu.cn.doctor.util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 统一返回对象R
 *
 * @author makejava
 * @since 2024-06-20 14:48:49
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
  
    private Integer code; //状态码, 1成功, 0失败
    private T Data;
    private String message;

    // 成功的静态方法
    public static <T> Result<T> success(T data) {
        return new Result<>(1, data, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(1, null, null);
    }

    // 失败的静态方法
    public static <T> Result<T> error(String message) {
        return new Result<>(0, null, message);
    }
}

