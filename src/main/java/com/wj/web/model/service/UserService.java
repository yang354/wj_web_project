package com.wj.web.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.web.vo.UserInfoVO;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author yyyz
 * @since 2022-09-25
 */
public interface UserService extends IService<User> {
/**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserByUserName(String username);

    /**
     * 获取用户列表
     * @param page
     * @return
     */
    IPage<User> getUsers(IPage<User> page);

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    User findUserByPhone(String phone);

    /**
     * 根据用户停用该用户
     * @return
     */
    int closeUser(UserInfoVO userInfoVO);

    /**
     * 根据用户 id 添加允许用户登录的ip
     */
    int addUserAllowIp(User user);
}
