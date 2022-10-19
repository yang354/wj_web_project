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
 * @TableName wj_gift_card
 */
@TableName(value ="wj_gift_card")
@Data
public class GiftCard implements Serializable {
    /**
     * 礼品卡id
     */
    @TableId(type = IdType.AUTO)
    private Integer giftCardId;

    /**
     * 礼品卡类型(1、星巴克 2、亚马逊 3、VISA 4、香草VISA 5、其它)
     */
    private Integer giftCardType;

    /**
     * 地区
     */
    private String area;

    /**
     * 卡密或卡号
     */
    private String cardcode;

    /**
     * 编号
     */
    private String code;

    /**
     * cvv
     */
    private String cvv;

    /**
     * 日期
     */
    private Date createtime;

    /**
     * 面值
     */
    private Integer facevalue;

    /**
     * 兑换id
     */
    private Integer exchangeId;

    /**
     *删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

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
        GiftCard other = (GiftCard) that;
        return (this.getGiftCardId() == null ? other.getGiftCardId() == null : this.getGiftCardId().equals(other.getGiftCardId()))
            && (this.getGiftCardType() == null ? other.getGiftCardType() == null : this.getGiftCardType().equals(other.getGiftCardType()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getCardcode() == null ? other.getCardcode() == null : this.getCardcode().equals(other.getCardcode()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getCvv() == null ? other.getCvv() == null : this.getCvv().equals(other.getCvv()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getFacevalue() == null ? other.getFacevalue() == null : this.getFacevalue().equals(other.getFacevalue()))
            && (this.getExchangeId() == null ? other.getExchangeId() == null : this.getExchangeId().equals(other.getExchangeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGiftCardId() == null) ? 0 : getGiftCardId().hashCode());
        result = prime * result + ((getGiftCardType() == null) ? 0 : getGiftCardType().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getCardcode() == null) ? 0 : getCardcode().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCvv() == null) ? 0 : getCvv().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getFacevalue() == null) ? 0 : getFacevalue().hashCode());
        result = prime * result + ((getExchangeId() == null) ? 0 : getExchangeId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", giftCardId=").append(giftCardId);
        sb.append(", giftCardType=").append(giftCardType);
        sb.append(", area=").append(area);
        sb.append(", cardcode=").append(cardcode);
        sb.append(", code=").append(code);
        sb.append(", cvv=").append(cvv);
        sb.append(", createtime=").append(createtime);
        sb.append(", facevalue=").append(facevalue);
        sb.append(", exchangeId=").append(exchangeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}