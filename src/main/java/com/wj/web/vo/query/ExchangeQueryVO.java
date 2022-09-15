package com.wj.web.vo.query;

import lombok.Data;

@Data
public class ExchangeQueryVO {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
}
