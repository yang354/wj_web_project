package com.wj.web.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.entity.User;
import com.wj.web.model.dao.UserMapper;
import com.wj.web.model.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yyyz
 * @since 2022-09-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public User findUserByUserName(String username) {
        //创建条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(); //用户名
        queryWrapper.eq("user_name",username);
        //返回查询记录
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> getUsers(IPage<User> page) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().orderBy(true,true,User::getCreateTime);
        userQueryWrapper.select("user_name");
        return baseMapper.selectPage(page,userQueryWrapper);
    }


}
