package com.wj.web.model.controller;


import com.wj.web.config.redis.RedisService;
import com.wj.web.model.entity.User;
import com.wj.web.model.service.UserService;
import com.wj.web.util.JwtUtils;
import com.wj.web.util.Result;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 登录
     *
     * @return
     */
    @ApiOperation("登录")
    @ApiImplicitParam(value = "登录",name = "userLoginVO",dataType = "UserLoginVO")
    @GetMapping("/userLogin")
    public Result userLogin(@RequestBody UserLoginVO userLoginVO) {
        // TODO: 2022/9/15
        return Result.ok();
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

        //创建用户信息对象
        UserInfoVO userInfo = new UserInfoVO();
        BeanUtils.copyProperties(user,userInfo);

        //UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), user.getAvatar(),user.getPhone(),userType.getTypeName());
        //返回数据
        return Result.ok(userInfo);
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

}

