package com.wj.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName wj_exchange
 */
@TableName(value ="wj_exchange")
@Data
public class Exchange implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 修改人
     */
    private Long updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Exchange other = (Exchange) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPayee() == null ? other.getPayee() == null : this.getPayee().equals(other.getPayee()))
            && (this.getPayment() == null ? other.getPayment() == null : this.getPayment().equals(other.getPayment()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDealTime() == null ? other.getDealTime() == null : this.getDealTime().equals(other.getDealTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getStartEmail() == null ? other.getStartEmail() == null : this.getStartEmail().equals(other.getStartEmail()))
            && (this.getEndEmail() == null ? other.getEndEmail() == null : this.getEndEmail().equals(other.getEndEmail()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getGiftCardId() == null ? other.getGiftCardId() == null : this.getGiftCardId().equals(other.getGiftCardId()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPayee() == null) ? 0 : getPayee().hashCode());
        result = prime * result + ((getPayment() == null) ? 0 : getPayment().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDealTime() == null) ? 0 : getDealTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getStartEmail() == null) ? 0 : getStartEmail().hashCode());
        result = prime * result + ((getEndEmail() == null) ? 0 : getEndEmail().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getGiftCardId() == null) ? 0 : getGiftCardId().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payee=").append(payee);
        sb.append(", payment=").append(payment);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", dealTime=").append(dealTime);
        sb.append(", status=").append(status);
        sb.append(", money=").append(money);
        sb.append(", startEmail=").append(startEmail);
        sb.append(", endEmail=").append(endEmail);
        sb.append(", code=").append(code);
        sb.append(", giftCardId=").append(giftCardId);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}