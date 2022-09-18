package com.wj.web.model.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wj.web.model.entity.Exchange;
import com.wj.web.model.service.ExchangeService;
import com.wj.web.util.Result;
import com.wj.web.vo.add.ExchangeAddVO;
import com.wj.web.vo.query.ExchangeQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@Api(value = "回收礼品",tags = "回收礼品",description = "回收礼品")
@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;


    /**
     * 添加一条兑换
     * @return
     */
    @ApiOperation("添加一条兑换")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "兑换对象",name = "exchangeAddVO",dataType = "ExchangeAddVO"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping("/add")
    public Result add(@RequestBody ExchangeAddVO exchangeAddVO) {

        exchangeService.add(exchangeAddVO);
        return Result.ok().msg("添加一条兑换成功");

    }


    /**
     * 获取兑换列表
     *
     * @param exchangeQueryVO
     * @return
     */
    @ApiOperation("获取兑换列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "获取兑换列表",name = "exchangeQueryVO",dataType = "ExchangeQueryVO"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping("/list")
    public Result list(@RequestBody ExchangeQueryVO exchangeQueryVO) {
        //创建分页对象
        IPage<Exchange> page = new Page<Exchange>(exchangeQueryVO.getPageNo(), exchangeQueryVO.getPageSize());
        //调用分页查询方法
        exchangeService.getExchanges(page, exchangeQueryVO);
        //返回数据
        return Result.ok(page);
    }

}

