package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName overall_result
 */
@TableName(value ="overall_result")
@Data
public class OverallResult implements Serializable {
    /**
     * 总检结论项编号
     */
    @TableId(value = "orId", type = IdType.AUTO)
    private Integer orId;

    /**
     * 总检结论项标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 总检结论项内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 所属预约编号
     */
    @TableField(value = "orderId")
    private Integer orderId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}