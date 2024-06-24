package com.scau.edu.cn.doctor.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private String userId;
    private String realName;
    private Integer sex;
    private Integer smId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderDate;
    private Integer state;
    private Integer page;
    private Integer pageSize;
}
