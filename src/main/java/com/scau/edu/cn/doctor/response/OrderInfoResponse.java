package com.scau.edu.cn.doctor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scau.edu.cn.doctor.domain.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoResponse {

    /**订单编号*/private Integer orderId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    /**预约日期*/private Date orderDate;
    /**客户编号*/private String userId;
    /**所属医院编号*/private String hpName;
    /**所属套餐编号*/private Integer smName;
    /**客户性别*/private String sex;
    /**客户姓名*/private String realName;
}
