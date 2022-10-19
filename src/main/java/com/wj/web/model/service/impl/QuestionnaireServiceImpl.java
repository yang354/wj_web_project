package com.wj.web.model.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wj.web.config.redis.RedisService;
import com.wj.web.vo.query.QuestionnaireQueryVO;
import com.wj.web.model.dao.QuestionnaireMapper;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.service.QuestionnaireService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@Slf4j
@Transactional
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {

//    @Cacheable(value = "questionnaires",key = "'getQuestionnaires'",sync = true)
    @Override
    public IPage<Questionnaire> getQuestionnaires(IPage<Questionnaire> page, QuestionnaireQueryVO questionnaireQueryVO) {
        QueryWrapper<Questionnaire> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",0);
        if (!ObjectUtils.isEmpty(questionnaireQueryVO.getContent())){
            wrapper.like("content",questionnaireQueryVO.getContent());
        }
        if(!ObjectUtils.isEmpty(questionnaireQueryVO.getType())){
            wrapper.eq("type",questionnaireQueryVO.getType());
        }
        wrapper.orderByDesc("create_time");
        return baseMapper.selectPage(page, wrapper);
    }

//    @Cacheable(value = "questionnaires",key = "'getQuestionnaireById'",sync = true)
    @Override
    public Questionnaire getQuestionnaireById(Integer questionnaireId) {
        Questionnaire questionnaire = baseMapper.selectById(questionnaireId);
        return questionnaire;
    }

}
