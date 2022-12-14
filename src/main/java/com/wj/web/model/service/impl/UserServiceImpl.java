package com.wj.web.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.web.model.dao.UserMapper;
import com.wj.web.model.entity.User;
import com.wj.web.model.service.UserService;
import com.wj.web.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        queryWrapper.eq("del_flag",0);
        //返回查询记录
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> getUsers(IPage<User> page) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().orderBy(true,true,User::getCreateTime);
        userQueryWrapper.select("user_name");
        userQueryWrapper.eq("del_flag",0);
        return baseMapper.selectPage(page,userQueryWrapper);
    }

    @Override
    public User findUserByPhone(String phone) {
        //创建条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(); //用户名
        queryWrapper.eq("phonenumber",phone);
        queryWrapper.eq("del_flag",0);
        //返回查询记录
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public int closeUser(UserInfoVO userInfoVO) {
        User user = new User();
        BeanUtils.copyProperties(userInfoVO,user);
        //设置停用
        user.setStatus("1");
        return baseMapper.updateById(user);
    }

    @Override
    public int addUserAllowIp(User user) {
        return baseMapper.updateById(user);
    }


}
