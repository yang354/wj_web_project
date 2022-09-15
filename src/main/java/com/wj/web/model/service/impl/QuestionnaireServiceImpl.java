package com.wj.web.model.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wj.web.vo.query.QuestionnaireQueryVO;
import com.wj.web.model.dao.QuestionnaireMapper;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.service.QuestionnaireService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;


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
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {


    @Override
    public IPage<Questionnaire> getQuestionnaires(IPage<Questionnaire> page, QuestionnaireQueryVO questionnaireQueryVO) {
        QueryWrapper<Questionnaire> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(questionnaireQueryVO.getCreateTime())){
            wrapper.like("create_time",questionnaireQueryVO.getCreateTime());
        }

        wrapper.orderByAsc("create_time");
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public Questionnaire getQuestionnaireById(Integer questionnaireId) {
        Questionnaire questionnaire = baseMapper.selectById(questionnaireId);

        return questionnaire;
    }
}
