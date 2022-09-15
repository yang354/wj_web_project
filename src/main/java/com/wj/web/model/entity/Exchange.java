package com.wj.web.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("wj_exchange")
public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收款人
     */
    private String payee;

    /**
     * 收款方式
     */
    private String payment;

    /**
     * 收款人电话
     */
    private String phone;

    /**
     * 提交时间
     */
    private Date createTime;

    /**
     * 处理时间
     */
    private Date dealTime;

    /**
     * 处理状态
     */
    private Integer status;

    /**
     * 打款金额
     */
    private Integer money;

    /**
     * 发件邮箱
     */
    private String startEmail;

    /**
     * 目的地邮箱
     */
    private String endEmail;

    /**
     * 收款码
     */
    private String code;

    /**
     * 礼品卡id
     */
    private Integer giftCardId;


}
