package com.wj.web.vo;

import lombok.Data;



@Data
public class UserLoginVO {

    /**
     * 登录名称(用户名)
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

}
