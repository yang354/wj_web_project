package com.wj.web.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
