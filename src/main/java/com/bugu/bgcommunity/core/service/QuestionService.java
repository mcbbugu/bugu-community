package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.core.mapper.QuestionMapper;
import com.bugu.bgcommunity.enums.ResultEnum;
import com.bugu.bgcommunity.exception.BuguException;
import com.bugu.bgcommunity.core.model.dto.QuestionDTO;
import com.bugu.bgcommunity.core.model.entity.Question;
import com.bugu.bgcommunity.core.model.entity.User;
import com.bugu.bgcommunity.core.model.form.QuestionForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:52
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QuestionService{

    private final QuestionMapper questionMapper;
    private final UserService userService;

    public IPage<QuestionDTO> findQuestionBy(Page<QuestionDTO> page, String tag, String sort){
        IPage<QuestionDTO> questions = questionMapper.findQuestionByTag(page, tag, sort);
        if(null == questions){
            throw new BuguException(ResultEnum.no_question);
        }
        return questions;
    }

    public void addQuestion(QuestionForm form, String cookie){
        User user = userService.findUserBy(cookie);
        if(user != null){
            Question question = new Question();
            question.setTitle(form.getTitle());
            question.setContent(form.getContent());
            List<String> tags = form.getTags();
            String strTags = String.join(",", tags);
            question.setTags(strTags);
            question.setUserId(user.getId());
            questionMapper.insert(question);
        }
    }
}