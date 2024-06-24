package com.scau.edu.cn.doctor.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidetailedReportDto {
    private String orderId;
    private String ciId;
    private String name;
    private String value;
}
