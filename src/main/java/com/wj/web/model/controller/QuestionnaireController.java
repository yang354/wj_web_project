package com.wj.web.model.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wj.web.vo.query.QuestionnaireQueryVO;
import com.wj.web.model.entity.Questionnaire;
import com.wj.web.model.service.QuestionnaireService;
import com.wj.web.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yyyz
 * @since 2022-09-15
 */
@Api(value = "问卷管理", tags = "问卷管理", description = "问卷管理")
@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;


    /**
     * 获取一篇问卷详情
     *
     * @param questionnaireId
     * @return
     */
    @ApiOperation("查问卷详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = false),
            @ApiImplicitParam(value = "问卷Id", name = "questionnaireId", dataType = "Integer")
    })
    @PostMapping("/getLawUserById")
    public Result getLawUserById(Integer questionnaireId) {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(questionnaireId);
        return Result.ok(questionnaire).msg("获取一篇问卷详情成功");
    }


    /**
     * 获取问卷列表
     *
     * @param questionnaireQueryVO
     * @return
     */
    @ApiOperation("获取问卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "获取问卷列表", name = "questionnaireQueryVO", dataType = "QuestionnaireQueryVO"),
            @ApiImplicitParam(paramType = "header", name = "token", required = false)
    })
    @PostMapping("/list")
    public Result list(@RequestBody QuestionnaireQueryVO questionnaireQueryVO) {
        //创建分页对象
        IPage<Questionnaire> page = new Page<Questionnaire>(questionnaireQueryVO.getPageNo(), questionnaireQueryVO.getPageSize());
        //调用分页查询方法
        questionnaireService.getQuestionnaires(page, questionnaireQueryVO);
        //返回数据
        return Result.ok(page);
    }
}

