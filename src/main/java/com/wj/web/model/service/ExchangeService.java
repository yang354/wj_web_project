package com.wj.web.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.web.model.entity.Exchange;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.web.vo.add.ExchangeAddVO;
import com.wj.web.vo.query.ExchangeQueryVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
public interface ExchangeService extends IService<Exchange> {

    IPage<Exchange> getExchanges(IPage<Exchange> page, ExchangeQueryVO exchangeQueryVO);

    void add(ExchangeAddVO exchangeAddVO);

}
