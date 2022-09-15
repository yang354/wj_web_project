package com.wj.web.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.web.exception.MyException;
import com.wj.web.model.entity.Exchange;
import com.wj.web.model.dao.ExchangeMapper;
import com.wj.web.model.service.ExchangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.web.util.ResultCode;
import com.wj.web.vo.add.ExchangeAddVO;
import com.wj.web.vo.query.ExchangeQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@Transactional
@Service
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange> implements ExchangeService {
    @Override
    public IPage<Exchange> getExchanges(IPage<Exchange> page, ExchangeQueryVO exchangeQueryVO) {
        QueryWrapper<Exchange> wrapper = new QueryWrapper<>();

        wrapper.orderByAsc("id");
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public void add(ExchangeAddVO exchangeAddVO) {
        Exchange exchange = new Exchange();
        BeanUtils.copyProperties(exchangeAddVO,exchange);
        int index = baseMapper.insert(exchange);
        if (index==0){
            throw new MyException(ResultCode.ERROR,"添加一条兑换失败");
        }
    }

}
