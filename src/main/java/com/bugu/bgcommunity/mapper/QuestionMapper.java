package com.bugu.bgcommunity.mapper;

import com.bugu.bgcommunity.model.dto.QuestionDTO;

import java.util.List;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:34
 */
public interface QuestionMapper {
    List<QuestionDTO> findQuestionByTag(String tag);
}