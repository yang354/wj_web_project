package com.wj.web.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.wj.web.vo.query.QuestionnaireQueryVO;
import com.wj.web.model.entity.Questionnaire;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
public interface QuestionnaireService extends IService<Questionnaire> {


    IPage<Questionnaire> getQuestionnaires(IPage<Questionnaire> page, QuestionnaireQueryVO questionnaireQueryVO);

    Questionnaire getQuestionnaireById(Integer questionnaireId);
}
