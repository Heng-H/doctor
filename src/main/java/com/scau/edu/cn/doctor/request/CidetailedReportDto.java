package com.scau.edu.cn.doctor.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidetailedReportDto {
    private Integer orderId;
    private Integer ciId;
    private String name;
    private String value;
}
