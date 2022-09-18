package com.wj.web.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.web.exception.MyException;
import com.wj.web.model.entity.Exchange;
import com.wj.web.model.entity.User;
import com.wj.web.model.service.ExchangeService;
import com.wj.web.model.dao.ExchangeMapper;
import com.wj.web.model.service.GiftCardService;
import com.wj.web.util.ResultCode;
import com.wj.web.vo.add.ExchangeAddVO;
import com.wj.web.vo.query.ExchangeQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author JT
 * @description 针对表【wj_exchange】的数据库操作Service实现
 * @createDate 2022-09-18 15:15:21
 */
@Service
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange>
        implements ExchangeService {
    @Autowired
    GiftCardService service;

    @Override
    public IPage<Exchange> getExchanges(IPage<Exchange> page, ExchangeQueryVO exchangeQueryVO) {
        QueryWrapper<Exchange> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public void add(ExchangeAddVO exchangeAddVO) {
        Exchange exchange = new Exchange();
        BeanUtils.copyProperties(exchangeAddVO, exchange);
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户信息
        User user = (User) authentication.getPrincipal();
        exchange.setCreateUser(user.getId());
        exchange.setUpdateUser(user.getId());
        int index = baseMapper.insert(exchange);
        if (index == 0) {
            throw new MyException(ResultCode.ERROR, "添加一条兑换失败");
        }
        if (!ObjectUtils.isEmpty(exchange)) {
            if (exchangeAddVO.getGiftCards() != null && exchangeAddVO.getGiftCards().size() != 0) {
                if (exchange.getId() != null) {
                    exchangeAddVO.getGiftCards().stream().forEach(item -> {
                        item.setExchangeId(exchange.getId());
                    });
                    boolean b = service.saveBatch(exchangeAddVO.getGiftCards());
                    if (!b) {
                        throw new MyException(ResultCode.ERROR, "添加礼品卡失败");
                    }
                }
            }
        }
    }

}




