package com.bugu.bgcommunity.service;

import com.bugu.bgcommunity.model.dto.QuestionDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<QuestionDTO> list = questionService.findQuestionBy("博客");
        System.out.println(list);
    }
}