package com.wj.web.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 用户信息表
 * </p>
 *
 * @author yyyz
 * @since 2022-09-25
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
      @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 头像
     */
    private String photo;

    /**
     * 允许用户登录的ip
     */
    private String allowIp;

//##########################################
    /**
     * 帐户是否过期(1-未过期，0-已过期)
     */
    @TableField(exist = false)
    private boolean isAccountNonExpired = true;

    /**
     * 帐户是否被锁定(1-未过期，0-已过期)
     */
    @TableField(exist = false)
    private boolean isAccountNonLocked = true;

    /**
     * 密码是否过期(1-未过期，0-已过期)
     */
    @TableField(exist = false)
    private boolean isCredentialsNonExpired = true;

    /**
     * 帐户是否可用(1-可用，0-禁用)
     */
    @TableField(exist = false)
    private boolean isEnabled = true;


/**
 * 这两个是一对的，应对权限业务的时候添加下面两个属性，只是授权认证登录只下面第一个，并直接给null
 * @TableField(exist = false)
 * Collection<? extends GrantedAuthority> authorities = null;
 */
    /**
     * 权限列表
     * */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return userName;
    }


//##################################################
}
