package com.wj.web.model.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wj.web.config.redis.RedisService;
import com.wj.web.exception.MyException;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.entity.User;
import com.wj.web.model.service.UserService;
import com.wj.web.util.JwtUtils;
import com.wj.web.util.Result;
import com.wj.web.util.ResultCode;
import com.wj.web.vo.TokenVO;
import com.wj.web.vo.UserInfoVO;
import com.wj.web.vo.UserLoginVO;
import com.wj.web.vo.query.QuestionnaireQueryVO;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@Api(value = "用户管理",tags = "用户管理",description = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {
@Resource
    private UserService userService;
    @Resource
    private RedisService redisService;
    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录
     *
     * @return
     */
    @ApiOperation("登录")
    @ApiImplicitParam(value = "登录",name = "userLoginVO",dataType = "UserLoginVO")
    @PostMapping("/login")
    public Result userLogin(@Validated UserLoginVO userLoginVO) {
        // TODO: 2022/9/15
        User userByPhone = userService.findUserByPhone(userLoginVO.getUsername());
        if(userByPhone == null){
            throw new MyException(ResultCode.ERROR, "登录账号不正确");
        }else{
            userLoginVO.setUsername(userByPhone.getUsername());
        }


        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginVO.getUsername(), userLoginVO.getPassword());
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new MyException(ResultCode.ERROR, "登录密码不正确");
            } else {
                throw new MyException(ResultCode.ERROR, e.getMessage());
            }
        }
        User loginUser = (User) authentication.getPrincipal();
        //生成token
        String token = jwtUtils.generateToken(loginUser);


        //把生成的token存到redis
        String tokenKey = "token_" + token;
        redisService.set(tokenKey, token, jwtUtils.getExpiration() / 1000);

        //设置token签名密钥及过期时间
        long expireTime = Jwts.parser() //获取DefaultJwtParser对象
                .setSigningKey(jwtUtils.getSecret()) //设置签名的密钥
                .parseClaimsJws(token.replace("jwt_", ""))  //把生成的jwt_前缀 替换为""
                .getBody().getExpiration().getTime();//获取token过期时间


        HashMap<String, Object> data = new HashMap<>();
        data.put("id", loginUser.getUserId());
        data.put("expireTime", expireTime);
        data.put("token", token);
        return Result.ok(data);
    }




    /**
     * 获取用户信息
     *
     * @return
     */
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(paramType = "header", name = "token", required = false)
    @GetMapping("/getInfo")
    public Result getInfo() {
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //判断authentication对象是否为空
        if (authentication == null) {
            return Result.error().msg("用户信息查询失败");
        }
        //获取用户信息
        User user = (User) authentication.getPrincipal();
//        System.out.println(user.toString());
//        String substring = user.getAvatar().substring(7);
//        user.setAvatar("/var/lib/docker/overlay2/08d0c3985cccc4e50c5cd56164176dc21d7f5729a9ca2b538366f9d08893e0b4/diff/home/ruoyi/uploadPath"+substring);

//        redisService.get("");
        //创建用户信息对象
        UserInfoVO userInfo = new UserInfoVO();
        BeanUtils.copyProperties(user,userInfo);

        userInfo.setEndTime(UserController.monthAddFrist(user.getCreateTime()));
        //UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), user.getAvatar(),user.getPhone(),userType.getTypeName());
        //返回数据
        return Result.ok(userInfo);
    }

    /**
     * 给时间加一个月
     * @param date
     * @return
     */
    public static Date monthAddFrist(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            //将Date类型 转 LocalDateTime类型
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            //加一个月
            LocalDateTime plusMonthsLocalDateTime = localDateTime.plusMonths(1);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //转 String
            String format = plusMonthsLocalDateTime.format(dateTimeFormatter);
            //转 Date
            return simpleDateFormat.parse(format);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }



    /**
     * 刷新token
     *
     * @param request
     * @return
     */
    @ApiOperation("刷新token")
    @ApiImplicitParam(value = "请求",name = "request",dataType = "HttpServletRequest")
    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        //从header中获取前端提交的token
        String token = request.getHeader("token");
        //如果header中没有token，则从参数中获取
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取身份信息
        UserDetails details = (UserDetails) authentication.getPrincipal();
        //重新生成token
        String reToken = "";
        //验证原来的token是否合法
        if (jwtUtils.validateToken(token, details)) {
            //生成新的token
            reToken = jwtUtils.refreshToken(token);
        }
        //获取本次token的到期时间，交给前端做判断
        long expireTime = Jwts.parser().setSigningKey(jwtUtils.getSecret())
                .parseClaimsJws(reToken.replace("jwt_", ""))
                .getBody().getExpiration().getTime();
        //清除原来的token信息
        String oldTokenKey = "token_" + token;
        redisService.del(oldTokenKey);
        //存储新的token
        String newTokenKey = "token_" + reToken;
        redisService.set(newTokenKey, reToken, jwtUtils.getExpiration() / 1000);
        //创建TokenVo对象
        TokenVO tokenVo = new TokenVO(expireTime, reToken);
        //返回数据
        return Result.ok(tokenVo).msg("token生成成功");
    }

    /**
     * 用户退出
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("用户退出")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求",name = "request",dataType = "HttpServletRequest"),
            @ApiImplicitParam(value = "响应",name = "response",dataType = "HttpServletResponse"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        //获取token
        String token = request.getParameter("token");
        //如果没有从头部获取token，那么从参数里面获取
        if (ObjectUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        //获取用户相关信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //清空用户信息
            new SecurityContextLogoutHandler().logout(request, response,authentication);
            //清空redis里面的token
            String key = "token_" + token;
            redisService.del(key);
        }
        return Result.ok().msg("用户退出成功");
    }

    /**
     * 获取问卷列表
     *
     * @return
     */
    @ApiOperation("获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @GetMapping("/list")
    public Result list() {
        //创建分页对象
        IPage<User> page = new Page<User>(1, 5);
        //调用分页查询方法
        userService.getUsers(page);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 根据用户id停用用户
     * @param userInfoVO
     * @return
     */
    @ApiOperation("根据用户id停用用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping("/close")
    public Result closeUser(UserInfoVO userInfoVO){
        //调用停用方法
        userService.closeUser(userInfoVO);
        //返回数据
        return Result.ok();
    }
}

