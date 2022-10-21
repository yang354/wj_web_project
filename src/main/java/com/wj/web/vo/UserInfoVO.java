package com.wj.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO implements Serializable {
    private Long userId;//用户ID
    private String username;//用户名称
    private String photo;//头像
    private String phone;//手机号
    private Date endTime;//结束时间


}