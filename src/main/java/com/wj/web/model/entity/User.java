package com.wj.web.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wj_user")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名称(用户名)
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 帐户是否过期(1-未过期，0-已过期)
     */
    private boolean isAccountNonExpired = true;

    /**
     * 帐户是否被锁定(1-未过期，0-已过期)
     */
    private boolean isAccountNonLocked = true;

    /**
     * 密码是否过期(1-未过期，0-已过期)
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * 帐户是否可用(1-可用，0-禁用)
     */
    private boolean isEnabled = true;

    /**
     * 权限列表
     * */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities = null;

    /**
     * 性别(0-男，1-女)
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)  //自动填充
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)  //自动填充
    private Date updateTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    @TableLogic
    private Integer isDelete;



}
