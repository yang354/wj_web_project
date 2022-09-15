package com.wj.web.vo.query;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionnaireQueryVO {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Date createTime;
}
