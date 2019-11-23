package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.core.model.dto.QuestionDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 22:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    void findQuestionBy() {
        Page<QuestionDTO> page = new Page<>(1, 2);
        IPage<QuestionDTO> questions = questionService.findQuestionBy(page, "博客", "id");
        System.out.println(questions.getRecords());
    }
}