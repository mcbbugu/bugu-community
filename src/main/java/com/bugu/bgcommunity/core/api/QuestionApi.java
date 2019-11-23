package com.bugu.bgcommunity.core.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.common.annotation.RRestController;
import com.bugu.bgcommunity.core.service.QuestionService;
import com.bugu.bgcommunity.enums.ResultEnum;
import com.bugu.bgcommunity.exception.BuguException;
import com.bugu.bgcommunity.core.model.dto.QuestionDTO;
import com.bugu.bgcommunity.core.model.form.QuestionForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * .
 * Created by mcbbugu
 * 2019-10-29 23:28
 */
@Slf4j
@RRestController("/question/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QuestionApi {

    private final QuestionService questionService;

    @GetMapping("find")
    public ResultDTO findQuestionBy(@RequestParam(defaultValue = "1") int current,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String tag,
                                    @RequestParam(defaultValue = "gmt_create") String sort){
        Page<QuestionDTO> page = new Page<>(current, size);
        IPage<QuestionDTO> questions = questionService.findQuestionBy(page, tag, sort);
        return ResultDTO.ok(questions);
    }

    @PostMapping("add")
    public ResultDTO addQuestion(@Valid QuestionForm form,
                                 BindingResult result,
                                 HttpServletRequest request){
        if(result.hasErrors()) {
            log.info(ResultEnum.param_error, form);
            throw new BuguException(result.getFieldError().getDefaultMessage());
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("bgcommunity-token")){
                String cookieValue = cookie.getValue();
                questionService.addQuestion(form, cookieValue);
                return ResultDTO.ok(ResultEnum.success_send);
            }
        }
        return ResultDTO.error(ResultEnum.no_login);
    }
}