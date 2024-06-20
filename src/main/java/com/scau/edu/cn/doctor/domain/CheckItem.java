package com.scau.edu.cn.doctor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName check_item
 */
@TableName(value ="check_item")
@Data
public class CheckItem implements Serializable {
    /**
     * 检查项编号
     */
    @TableId(value = "ciId", type = IdType.AUTO)
    private Integer ciId;

    /**
     * 检查项名称
     */
    @TableField(value = "ciName")
    private String ciName;

    /**
     * 检查项内容
     */
    @TableField(value = "ciContent")
    private String ciContent;

    /**
     * 检查项意义
     */
    @TableField(value = "meaning")
    private String meaning;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}