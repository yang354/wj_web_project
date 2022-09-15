package com.wj.web.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wj_questionnaire")
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 金额
     */
    private Integer money;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 连接
     */
    private String link;

    @TableField(fill = FieldFill.INSERT)  //自动填充
    private Date createTime;


}
