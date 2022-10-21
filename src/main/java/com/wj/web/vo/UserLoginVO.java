package com.wj.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UserLoginVO {

    /**
     * 登录名称(用户名)
     */
    @NotBlank(message = "用户账号不能为空")
    private String username;

    /**
     * 登录密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;

    /**
     * 验证码
     */
    private String code;

}
