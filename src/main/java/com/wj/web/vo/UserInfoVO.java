package com.wj.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO implements Serializable {
    private Long id;//用户ID
    private String username;//用户名称
    private String avatar;//头像
    private String phone;//手机号


}