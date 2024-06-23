package com.scau.edu.cn.doctor.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String docId;
    private String docCode;
    private String docIdOrCode;
    private String password;
    private String code;


}
