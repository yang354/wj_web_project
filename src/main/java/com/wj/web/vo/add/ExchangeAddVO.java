package com.wj.web.vo.add;

import lombok.Data;

@Data
public class ExchangeAddVO {
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
