package com.wj.web.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.web.model.entity.Exchange;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.web.vo.add.ExchangeAddVO;
import com.wj.web.vo.query.ExchangeQueryVO;

/**
 * @author JT
 * @description 针对表【wj_exchange】的数据库操作Service
 * @createDate 2022-09-18 15:15:21
 */
public interface ExchangeService extends IService<Exchange> {
    IPage<Exchange> getExchanges(IPage<Exchange> page, ExchangeQueryVO exchangeQueryVO);

    void add(ExchangeAddVO exchangeAddVO);

}
