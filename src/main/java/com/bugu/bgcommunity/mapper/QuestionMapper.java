package com.bugu.bgcommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.model.dto.QuestionDTO;
import com.bugu.bgcommunity.model.entity.Question;
import org.apache.ibatis.annotations.Param;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:34
 */
public interface QuestionMapper extends BaseMapper<Question> {
    IPage<QuestionDTO> findQuestionByTag(Page page,
                                         @Param("tag") String tag,
                                         @Param("sort") String sort);
}