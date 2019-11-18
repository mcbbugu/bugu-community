package com.bugu.bgcommunity.service;

import com.bugu.bgcommunity.enums.ResultEnum;
import com.bugu.bgcommunity.exception.BuguException;
import com.bugu.bgcommunity.mapper.QuestionMapper;
import com.bugu.bgcommunity.model.dto.QuestionDTO;
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
public class QuestionService {

    private final QuestionMapper questionMapper;

    public List<QuestionDTO> findQuestionBy(String tag){
        List<QuestionDTO> questionDTO = questionMapper.findQuestionByTag(tag);
        if(null == questionDTO){
            throw new BuguException(ResultEnum.NO_QUESTION);
        }
        return questionDTO;
    }
}